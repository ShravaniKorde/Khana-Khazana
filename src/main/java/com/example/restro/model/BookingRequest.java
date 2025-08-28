package com.example.restro.model;

import lombok.Data;

@Data
public class BookingRequest {

    private String name;
    private String email;
    private String date;
    private String bookingTime;
    private Integer headCount;

}
