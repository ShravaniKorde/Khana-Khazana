package com.example.restro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class BookingDetails {

    @Id
    private Integer booking_id;
    private Integer rest_id;
    private Integer user_id;
    private Integer table_id;
    private String open_time;
    private String close_time;

    private enum booking_status {
        pending, confirm, cancelled
    }
    private Integer people;
}
