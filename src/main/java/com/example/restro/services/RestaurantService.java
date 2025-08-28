package com.example.restro.services;

import com.example.restro.entity.RestaurantDetails;
import com.example.restro.model.RestaurantResponse;
import com.example.restro.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;

    }

    public List<RestaurantResponse> getAllRestaurants() {

        List<RestaurantDetails> restaurants = restaurantRepository.findAll();

        List<RestaurantResponse> responseList = new ArrayList<>();
        for (RestaurantDetails restaurant : restaurants) {
            RestaurantResponse response = RestaurantResponse.builder()
                    .restId(restaurant.getRestId())
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .cuisine(restaurant.getCuisine())
                    .build();

            responseList.add(response);
        }

        return responseList;
    }
}
