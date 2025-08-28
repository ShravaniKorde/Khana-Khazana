package com.example.restro.entity;

import com.example.restro.model.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "bookings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {

    @Id
    @SequenceGenerator(name = "booking_id_seq", sequenceName = "booking_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq")
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "booking_user_fk", referencedColumnName = "user_id")
    private UserDetails userDetails;

    private LocalDate bookingDate;

    private LocalTime bookingTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    private Integer headCount;
}
