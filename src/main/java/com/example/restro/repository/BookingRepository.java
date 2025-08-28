package com.example.restro.repository;

import com.example.restro.entity.BookingDetails;
import com.example.restro.entity.UserDetails;
import com.example.restro.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetails, Integer> {

    @Query(value = "SELECT COUNT(*) FROM bookings b " +
            "JOIN user_details u ON b.booking_user_fk = u.user_id " +
            "WHERE u.email = :email", nativeQuery = true)
    int countBookingsByEmail(@Param("email") String email);

    BookingDetails findByUserDetails(UserDetails userDetails);

    List<BookingDetails> findByBookingDateAndStatus(LocalDate bookingDate, BookingStatus status);

}
