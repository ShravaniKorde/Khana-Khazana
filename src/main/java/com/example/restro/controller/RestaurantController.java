package com.example.restro.controller;

import com.example.restro.model.RestaurantResponse;
import com.example.restro.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping("/read")
    public ResponseEntity<List<RestaurantResponse>> getAllBookings() {
        return new ResponseEntity<>(this.restaurantService.getAllRestaurants(), HttpStatus.OK);
    }
}
