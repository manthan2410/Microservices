package com.reviewSystem.userService.controller;

import com.reviewSystem.userService.entities.User;

import com.reviewSystem.userService.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    @PostMapping
   public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1=userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

//    int retryCount=1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallbackMethod")
//    @Retry(name="ratingHotelRetry",fallbackMethod = "ratingHotelFallbackMethod")
    @RateLimiter(name="ratingHotelLimiter", fallbackMethod = "ratingHotelFallbackMethod")
    public ResponseEntity<User> getUserBYId(@PathVariable String userId)
    {
//        logger.info("retry count {}",retryCount);
//        retryCount++;
        User user1=userService.getUserById(userId);
      return   ResponseEntity.status(HttpStatus.OK).body(user1);
    }
//fallback mtd for ratingHotelBreaker circuit breaker
 public ResponseEntity<User>  ratingHotelFallbackMethod(String userId,Exception ex){
       ex.printStackTrace();
        logger.info(ex.getMessage(),"Service down");
      User user=User.builder().userId("").name("dummy").about("test").email("dummay@gmail.com").build();
       return new ResponseEntity<>(user,HttpStatus.OK);
  }

    @GetMapping
//   @PreAuthorize("hasAuthority('SCOPE_internal') ")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users= userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
