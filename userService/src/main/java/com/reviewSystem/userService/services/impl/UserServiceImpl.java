package com.reviewSystem.userService.services.impl;


import com.reviewSystem.userService.entities.Hotel;
import com.reviewSystem.userService.entities.Rating;
import com.reviewSystem.userService.entities.User;
import com.reviewSystem.userService.exteptions.ResourceNotFoundException;
import com.reviewSystem.userService.external.services.HotelService;
import com.reviewSystem.userService.repositories.UserRepository;
import com.reviewSystem.userService.services.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;
    @Override
    public User saveUser(User user) {
        String uId= UUID.randomUUID().toString();
        user.setUserId(uId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Not found"+ userId));
 Rating[] ratings= restTemplate.getForObject("http://RATINGSERVICE/rating/users/"+userId, Rating[].class);
    List<Rating> ratingsList= Arrays.stream(ratings).toList();
     logger.info("{}",ratings);

  List<Rating>  ratingWithHotelDetails =ratingsList.stream().map(rating -> {
      // ResTemplate communication
//   ResponseEntity<Hotel> ob= restTemplate.getForEntity("http://HOTELSERVICE/hotel/"+rating.getHotelId(), Hotel.class);
//    rating.setHotel(ob.getBody());
//    logger.info("{}",ob.getStatusCode());
//     System.out.println("statuscode"+ob.getBody());
   Hotel hotel=hotelService.getHotel(rating.getHotelId());
   rating.setHotel(hotel);
    return rating;
     }).collect(Collectors.toList());

     user.setRatings(ratingWithHotelDetails);
        return user;

    }
}