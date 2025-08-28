package com.example.restro.model;


import com.example.restro.model.BookingStatus;
import lombok.Data;

@Data
public class BookingFilterRequest {

    private String date;
    private BookingStatus status;

}

