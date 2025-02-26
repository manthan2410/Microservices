package com.reviewSystem.hotelService.controllers;

import com.reviewSystem.hotelService.entities.Hotel;
import com.reviewSystem.hotelService.services.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/hotel")
@RestController
public class HotelControllers {

    @Autowired
    private HotelServiceImpl hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') ")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }
}
