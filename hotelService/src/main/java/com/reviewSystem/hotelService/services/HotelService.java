package com.reviewSystem.hotelService.services;

import com.reviewSystem.hotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    Hotel getHotelById(String hotelId);

    List<Hotel> getAllHotels();
}
