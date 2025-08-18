package com.example.restro.services;

import com.example.restro.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;

    }
}
