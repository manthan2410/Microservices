package com.reviewSystem.userService.external.services;

import com.reviewSystem.userService.entities.Rating;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {

    @PostMapping("/rating")
    Rating createRating(Rating rating);

    @PutMapping("/rating/{ratingId}")
    Rating updateRating(@PathVariable String ratingId,Rating rating);

    @DeleteMapping("/rating/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
