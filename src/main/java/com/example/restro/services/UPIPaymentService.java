package com.example.restro.services;

import org.springframework.stereotype.Service;

@Service("upiPayment")
public class UPIPaymentService implements PaymentService{

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " using UPI";

    }
}
