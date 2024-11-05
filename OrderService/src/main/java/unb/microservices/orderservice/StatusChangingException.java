package unb.microservices.orderservice;

public class StatusChangingException extends Exception{
    public StatusChangingException(String message){
        super(message);
    }
}
