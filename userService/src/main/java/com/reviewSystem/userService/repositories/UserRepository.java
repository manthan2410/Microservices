package com.reviewSystem.userService.repositories;

import com.reviewSystem.userService.entities.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
