package com.example.restro.services;

import com.example.restro.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;

    }
}
