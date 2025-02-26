package com.reviewSystem.hotelService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping
    ResponseEntity<List<String>> getAllStaff()
    {
        List<String> staff= Arrays.asList("Ram","Shyam");
        return new ResponseEntity<>(staff,HttpStatus.OK );
    }
}
