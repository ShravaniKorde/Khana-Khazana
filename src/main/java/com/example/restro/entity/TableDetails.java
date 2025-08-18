package com.example.restro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tables")

public class TableDetails {

    @Id
    @SequenceGenerator(name = "table_id_seq", sequenceName = "table_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_id_seq")
    @Column(name = "table_id")
    private Integer table_id;

    @ManyToOne
    @JoinColumn(name = "table_rest_fk", referencedColumnName = "rest_id")
    private RestaurantDetails restaurantDetails;

    @Column(name = "seats")
    private Integer seats;

}
