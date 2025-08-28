package com.example.restro.controller;

import com.example.restro.model.BookingFilterRequest;
import com.example.restro.model.BookingRequest;
import com.example.restro.model.BookingResponse;
import com.example.restro.model.BookingStatus;
import com.example.restro.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest){
        bookingService.testPayment(500.0);
        return new ResponseEntity<>(bookingService.createBooking(bookingRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BookingResponse> updateBooking(@RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.updateBooking(bookingRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<BookingResponse> deleteUserRegistration(@PathVariable String email){
        return new ResponseEntity<>(this.bookingService.deleteBooking(email), HttpStatus.OK);
    }


    @GetMapping("/read")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return new ResponseEntity<>(this.bookingService.getAllBookings(), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<BookingResponse>> filterBookings(@RequestBody BookingFilterRequest request) {
        return new ResponseEntity<>(bookingService.filterBookings(request), HttpStatus.OK);
    }


}
