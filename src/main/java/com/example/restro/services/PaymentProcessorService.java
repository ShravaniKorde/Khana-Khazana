package com.example.restro.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessorService {

    private PaymentService paymentService;

    public PaymentProcessorService(@Qualifier("upiPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String processPayment(double amount) {
        return paymentService.pay(amount);
    }
}
