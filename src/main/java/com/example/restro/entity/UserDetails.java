package com.example.restro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigInteger;

@Entity
@Table(name = "user_details")
public class UserDetails{

    @Id
    private Integer user_id;
    private String name;
    private String email;
    private BigInteger phoneNumber;
    private String password;

}
