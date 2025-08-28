package com.example.restro.services;

import com.example.restro.entity.BookingDetails;
import com.example.restro.entity.UserDetails;
import com.example.restro.model.BookingFilterRequest;
import com.example.restro.model.BookingRequest;
import com.example.restro.model.BookingResponse;
import com.example.restro.model.BookingStatus;
import com.example.restro.repository.BookingRepository;
import com.example.restro.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private PaymentProcessorService paymentProcessorService;

    BookingService(BookingRepository bookingRepository, UserDetailsRepository userDetailsRepository, PaymentProcessorService paymentProcessorService) {
        this.bookingRepository = bookingRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.paymentProcessorService = paymentProcessorService;
    }

    public void testPayment(double amount) {
        String result = paymentProcessorService.processPayment(amount);
        log.info("Payment result: {}", result);
    }

    private UserDetails getUserOrThrow(String email) {
        UserDetails userDetails = userDetailsRepository.findByEmail(email);
        if (ObjectUtils.isEmpty(userDetails)) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return userDetails;
    }

    private BookingDetails getBookingOrThrow(UserDetails userDetails) {
        BookingDetails bookingDetails = bookingRepository.findByUserDetails(userDetails);
        if (ObjectUtils.isEmpty(bookingDetails)) {
            throw new RuntimeException("No booking found for user: " + userDetails.getEmail());
        }
        return bookingDetails;
    }

    private void validateBookingList(List<BookingDetails> bookings, String date, BookingStatus status) {
        if (ObjectUtils.isEmpty(bookings)) {
            throw new RuntimeException("No bookings found for date: " + date + " and status: " + status);
        }
    }


    public BookingResponse createBooking(BookingRequest request) {
        try {
            UserDetails userDetails = getUserOrThrow(request.getEmail());

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
                    .email(userDetails.getEmail())
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

            UserDetails userDetails = getUserOrThrow(request.getEmail());

            BookingDetails bookingDetails = getBookingOrThrow(userDetails);

            bookingDetails.setHeadCount(request.getHeadCount());
            bookingDetails.setBookingDate(LocalDate.parse(request.getDate(), DateTimeFormatter.BASIC_ISO_DATE));
            bookingDetails.setBookingTime(LocalTime.parse(request.getBookingTime(), DateTimeFormatter.ISO_LOCAL_TIME));
            bookingDetails.setStatus(BookingStatus.CONFIRM);

            bookingRepository.save(bookingDetails);

            return BookingResponse.builder()
                    .name(userDetails.getName())
                    .email(userDetails.getEmail())
                    .date(bookingDetails.getBookingDate().toString())
                    .bookingTime(bookingDetails.getBookingTime().toString())
                    .headCount(bookingDetails.getHeadCount())
                    .build();

        }catch (Exception e) {
            log.error("Error updating booking: {}", e.getMessage());
            throw new RuntimeException("Failed to update booking: " + e.getMessage());
        }
    }

    public BookingResponse deleteBooking(String email) {
        try {
            UserDetails userDetails = getUserOrThrow(email);

            BookingDetails bookingDetails = getBookingOrThrow(userDetails);

            bookingRepository.delete(bookingDetails);

            return BookingResponse.builder()
                    .name(userDetails.getName())
                    .email(userDetails.getEmail())
                    .date(bookingDetails.getBookingDate().toString())
                    .bookingTime(bookingDetails.getBookingTime().toString())
                    .headCount(bookingDetails.getHeadCount())
                    .build();

        }catch (Exception e) {
            log.error("Error deleting booking: {}", e.getMessage());
            throw new RuntimeException("Failed to delete booking: " + e.getMessage());
        }
    }

    public void testCountBookings(String email) {
        int count = bookingRepository.countBookingsByEmail(email);
        log.info("Current bookings for {}: {}", email, count);
    }

    @Value("${booking.max-tables}")
    private int maxTables;

    public void logMaxTables(String email) {
        int bookingCount = bookingRepository.countBookingsByEmail(email);
        log.info("Max tables allowed: {}", maxTables);
    }

    public List<BookingResponse> getAllBookings() {
        testCountBookings("pqr@pqr.com");
        logMaxTables("pqr@pqr.com");

        List<BookingDetails> bookings = bookingRepository.findAll();
        List<BookingResponse> responseList = new ArrayList<>();

        for (BookingDetails booking : bookings) {
            BookingResponse response = BookingResponse.builder()
                    .name(booking.getUserDetails().getName())
                    .email(booking.getUserDetails().getEmail())
                    .date(booking.getBookingDate().toString())
                    .bookingTime(booking.getBookingTime().toString())
                    .headCount(booking.getHeadCount())
                    .build();

            responseList.add(response);
        }

        return responseList;
    }

    public List<BookingResponse> filterBookings(BookingFilterRequest request) {
        try {
            LocalDate bookingDate = LocalDate.parse(request.getDate(), DateTimeFormatter.BASIC_ISO_DATE);
            BookingStatus status = request.getStatus();

            List<BookingDetails> bookings = bookingRepository.findByBookingDateAndStatus(bookingDate, status);
            validateBookingList(bookings, request.getDate(), status);

            List<BookingResponse> responseList = new ArrayList<>();
            for (BookingDetails booking : bookings) {
                BookingResponse response = BookingResponse.builder()
                        .name(booking.getUserDetails().getName())
                        .email(booking.getUserDetails().getEmail())
                        .date(booking.getBookingDate().toString())
                        .bookingTime(booking.getBookingTime().toString())
                        .headCount(booking.getHeadCount())
                        .build();

                responseList.add(response);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error filtering bookings: {}", e.getMessage());
            throw new RuntimeException("Failed to filter bookings: " + e.getMessage());
        }
    }


}
