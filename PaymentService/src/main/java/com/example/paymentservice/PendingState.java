package com.example.paymentservice;

public class PendingState implements PaymentState{
    @Override
    public void proccess(Payment payment) throws InsufficientFundsException {
        System.out.println("Payment Pending");
        try {            // Sleep for 5 seconds (5000 milliseconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle interrupted exception if necessary
            e.printStackTrace();
        }
        if(payment.getAmountInCard() >= payment.getAmount()){
            System.out.println("Payment Accepted");
            payment.changeState(new CompleteState());
            payment.proccess();
        }else {
            System.out.println("Payment Declined");
            payment.changeState(new FailedState());
            payment.proccess();
        }
    }
}
