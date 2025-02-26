package com.reviewSystem.hotelService.repositories;

import com.reviewSystem.hotelService.entities.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel,String> {
}
