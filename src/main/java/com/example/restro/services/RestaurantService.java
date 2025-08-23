package com.example.restro.services;

import com.example.restro.entity.RestaurantDetails;
import com.example.restro.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;

    }

    public List<RestaurantDetails> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
