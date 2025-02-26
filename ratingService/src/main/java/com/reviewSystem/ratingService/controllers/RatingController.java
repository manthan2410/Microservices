package com.reviewSystem.ratingService.controllers;

import com.reviewSystem.ratingService.entities.Rating;
import com.reviewSystem.ratingService.services.impl.RatingServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rating")
@RestController
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }
    @GetMapping
    public ResponseEntity<List<Rating>>  getAllRatings()
    {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }


    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId)
    {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId)
    {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}

