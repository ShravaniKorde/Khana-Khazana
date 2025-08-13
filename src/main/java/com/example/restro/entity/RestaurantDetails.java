package com.example.restro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantDetails {

    @Id
    private Integer rest_id;
    private String name;
    private String address;
    private String cuisine;
    private String open_time;
    private Integer no_of_tables;

}
