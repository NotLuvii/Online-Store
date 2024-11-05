package com.example.paymentservice;

public interface PaymentState {
    void proccess(Payment payment) throws InsufficientFundsException;
}
