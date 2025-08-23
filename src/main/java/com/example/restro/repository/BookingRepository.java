package com.example.restro.repository;

import com.example.restro.entity.BookingDetails;
import com.example.restro.entity.UserDetails;
import com.example.restro.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetails, Integer> {

    BookingDetails findByUserDetails(UserDetails userDetails);

    List<BookingDetails> findByBookingDateAndStatus(LocalDate bookingDate, BookingStatus status);

}
