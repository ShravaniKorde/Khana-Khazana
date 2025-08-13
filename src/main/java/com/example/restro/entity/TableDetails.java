package com.example.restro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")

public class TableDetails {

    @Id
    private Integer table_id;
    private Integer rest_id;
    private Integer seats;

}
