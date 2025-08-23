package com.example.restro.controller;

import com.example.restro.entity.BookingDetails;
import com.example.restro.entity.UserDetails;
import com.example.restro.model.BookingRequest;
import com.example.restro.model.BookingResponse;
import com.example.restro.model.BookingStatus;
import com.example.restro.model.RegistrationResponse;
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
        return new ResponseEntity<>(bookingService.createBooking(bookingRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BookingResponse> updateBooking(@RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.updateBooking(bookingRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<BookingResponse> deleteUserRegistration(@PathVariable String name){
        return new ResponseEntity<>(this.bookingService.deleteBooking(name), HttpStatus.OK);
    }


    @GetMapping("/read")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return new ResponseEntity<>(this.bookingService.getAllBookings(), HttpStatus.OK);
    }

    //http://localhost:8080/booking/filter?date=20250303&status=CONFIRM
    @GetMapping("/filter")
    public ResponseEntity<List<BookingResponse>> getBookingsByDateAndStatus(@RequestParam String date, @RequestParam BookingStatus status) {
        return new ResponseEntity<>(bookingService.getBookingsByDateAndStatus(date, status), HttpStatus.OK);
    }


}
