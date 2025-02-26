package com.reviewSystem.userService.services;

import com.reviewSystem.userService.entities.User;


import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);
}
