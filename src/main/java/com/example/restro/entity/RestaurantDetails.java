package com.example.restro.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name = "restaurant")
public class RestaurantDetails {

    @Id
    @SequenceGenerator(name = "rest_id_seq", sequenceName = "rest_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rest_id_seq")

    @Column(name = "rest_id")
    private Integer rest_id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "open_time")
    private Time open_time;

    @Column(name = "close_time")
    private Time close_time;

    @Column(name = "no_of_tables")
    private Integer no_of_tables;

}
