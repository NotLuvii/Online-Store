package com.example.paymentservice;

public class FailedState implements PaymentState{
    @Override
    public void proccess(Payment payment) throws InsufficientFundsException {
        System.out.println("Payment Failed");
        throw new InsufficientFundsException("Payment Failed");
    }
}
