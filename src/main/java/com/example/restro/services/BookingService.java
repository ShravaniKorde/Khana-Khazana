package com.example.restro.services;

import com.example.restro.entity.BookingDetails;
import com.example.restro.entity.UserDetails;
import com.example.restro.model.BookingRequest;
import com.example.restro.model.BookingResponse;
import com.example.restro.model.BookingStatus;
import com.example.restro.repository.BookingRepository;
import com.example.restro.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingService {

    private BookingRepository bookingRepository;
    private UserDetailsRepository userDetailsRepository;

    BookingService(BookingRepository bookingRepository, UserDetailsRepository userDetailsRepository) {
        this.bookingRepository = bookingRepository;
        this.userDetailsRepository = userDetailsRepository;
    }


    public BookingResponse createBooking(BookingRequest request) {
        try {

            UserDetails userDetails = userDetailsRepository.findByName(request.getName());

            if (userDetails == null) {
                throw new RuntimeException("User not found with name: " + request.getName());
            }

            BookingDetails bookingDetails = BookingDetails.builder()
                    .userDetails(userDetails)
                    .headCount(request.getHeadCount())
                    .bookingDate(LocalDate.parse(request.getDate(), DateTimeFormatter.BASIC_ISO_DATE))
                    .bookingTime(LocalTime.parse(request.getBookingTime(), DateTimeFormatter.ISO_LOCAL_TIME))
                    .status(BookingStatus.CONFIRM)
                    .build();

            bookingRepository.save(bookingDetails);

            return BookingResponse.builder()
                    .name(userDetails.getName())
                    .date(bookingDetails.getBookingDate().toString())
                    .bookingTime(bookingDetails.getBookingTime().toString())
                    .headCount(bookingDetails.getHeadCount())
                    .build();

        }catch (Exception e) {
            log.error("Error creating booking: {}", e.getMessage());
            throw new RuntimeException("Failed to create booking: " + e.getMessage());
        }
    }

    public BookingResponse updateBooking(BookingRequest request) {
        try {

            UserDetails userDetails = userDetailsRepository.findByName(request.getName());

            if (userDetails == null) {
                throw new RuntimeException("User not found with name: " + request.getName());
            }

            BookingDetails bookingDetails = bookingRepository.findByUserDetails(userDetails);
            if (bookingDetails == null) {
                throw new RuntimeException("No booking found for user: " + request.getName());
            }
            bookingDetails.setHeadCount(request.getHeadCount());
            bookingDetails.setBookingDate(LocalDate.parse(request.getDate(), DateTimeFormatter.BASIC_ISO_DATE));
            bookingDetails.setBookingTime(LocalTime.parse(request.getBookingTime(), DateTimeFormatter.ISO_LOCAL_TIME));
            bookingDetails.setStatus(BookingStatus.CONFIRM);

            bookingRepository.save(bookingDetails);

            return BookingResponse.builder()
                    .name(userDetails.getName())
                    .date(bookingDetails.getBookingDate().toString())
                    .bookingTime(bookingDetails.getBookingTime().toString())
                    .headCount(bookingDetails.getHeadCount())
                    .build();

        }catch (Exception e) {
            log.error("Error updating booking: {}", e.getMessage());
            throw new RuntimeException("Failed to update booking: " + e.getMessage());
        }
    }

    public BookingResponse deleteBooking(String name) {
        try {

            UserDetails userDetails = userDetailsRepository.findByName(name);
            if (userDetails == null) {
                throw new RuntimeException("User not found with name: " + name);
            }

            BookingDetails bookingDetails = bookingRepository.findByUserDetails(userDetails);
            if (bookingDetails == null) {
                throw new RuntimeException("No booking found for user: " + name);
            }

            bookingRepository.delete(bookingDetails);

            return BookingResponse.builder()
                    .name(userDetails.getName())
                    .date(bookingDetails.getBookingDate().toString())
                    .bookingTime(bookingDetails.getBookingTime().toString())
                    .headCount(bookingDetails.getHeadCount())
                    .build();

        }catch (Exception e) {
            log.error("Error deleting booking: {}", e.getMessage());
            throw new RuntimeException("Failed to delete booking: " + e.getMessage());
        }
    }

    public List<BookingResponse> getAllBookings() {
        List<BookingDetails> bookings = bookingRepository.findAll();
        List<BookingResponse> responseList = new ArrayList<>();

        for (BookingDetails booking : bookings) {
            BookingResponse response = BookingResponse.builder()
                    .name(booking.getUserDetails().getName())
                    .date(booking.getBookingDate().toString())
                    .bookingTime(booking.getBookingTime().toString())
                    .headCount(booking.getHeadCount())
                    .build();

            responseList.add(response);
        }

        return responseList;
    }

    public List<BookingResponse> getBookingsByDateAndStatus(String date, BookingStatus status) {
        try {
            LocalDate bookingDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);

            List<BookingDetails> bookings = bookingRepository.findByBookingDateAndStatus(bookingDate, status);

            if (bookings.isEmpty()) {
                throw new RuntimeException("No bookings found for date: " + date + " and status: " + status);
            }

            List<BookingResponse> responseList = new ArrayList<>();
            for (BookingDetails booking : bookings) {
                BookingResponse response = BookingResponse.builder()
                        .name(booking.getUserDetails().getName())
                        .date(booking.getBookingDate().toString())
                        .bookingTime(booking.getBookingTime().toString())
                        .headCount(booking.getHeadCount())
                        .build();

                responseList.add(response);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error fetching bookings: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch bookings: " + e.getMessage());
        }
    }


}
