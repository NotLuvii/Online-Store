package com.example.frontend;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
