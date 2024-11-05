package unb.microservices.orderservice;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
