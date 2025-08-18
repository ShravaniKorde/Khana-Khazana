package com.example.restro.entity;

import com.example.restro.model.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "bookings")
public class BookingDetails {

    @Id
    @SequenceGenerator(name = "booking_id_seq", sequenceName = "booking_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq")
    @Column(name = "booking_id")
    private Integer booking_id;

    @ManyToOne
    @JoinColumn(name = "booking_user_fk", referencedColumnName = "user_id")
    private UserDetails userDetails;

    @ManyToOne
    @JoinColumn(name = "booking_rest_fk", referencedColumnName = "rest_id")
    private RestaurantDetails restaurantDetails;

    @ManyToOne
    @JoinColumn(name = "booking_table_fk", referencedColumnName = "table_id")
    private TableDetails tableDetails;

    @Column(name = "bookingDate")
    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @Column(name = "headCount")
    private Integer headCount;
}
