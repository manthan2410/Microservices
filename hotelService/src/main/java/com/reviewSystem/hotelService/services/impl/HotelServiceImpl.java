package com.reviewSystem.hotelService.services.impl;

import com.reviewSystem.hotelService.entities.Hotel;
import com.reviewSystem.hotelService.exceptions.ResourceNotFoundException;
import com.reviewSystem.hotelService.repositories.HotelRepository;
import com.reviewSystem.hotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
   private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String hId= UUID.randomUUID().toString();
        hotel.setHotelId(hId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->
            new ResourceNotFoundException("hotel not found"+hotelId)
        );
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
