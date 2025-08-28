package com.example.restro.services;

import org.springframework.stereotype.Service;

@Service("creditCardPayment")
public class CreditCardPaymentService implements PaymentService{

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " using Credit Card";

    }
}
