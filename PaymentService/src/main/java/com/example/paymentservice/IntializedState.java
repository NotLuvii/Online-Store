package com.example.paymentservice;

public class IntializedState implements PaymentState{
    @Override
    public void proccess(Payment payment) throws InsufficientFundsException {
        System.out.println("Payment Initialized");
        payment.changeState(new PendingState());
        payment.proccess();
    }
}
