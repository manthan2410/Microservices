package com.reviewSystem.userService;

import com.netflix.discovery.converters.Auto;
import com.reviewSystem.userService.entities.Rating;
import com.reviewSystem.userService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
//to autowire things make it as service
@Service
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {
		Rating rating = Rating.builder().userId("test").rating(3).hotelId("").feedback("Testing Feign client from userservice").build();
		Rating rs = ratingService.createRating(rating);
		System.out.println("new Rating created");
	}
}
