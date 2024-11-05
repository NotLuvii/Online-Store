package com.example.paymentservice;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
