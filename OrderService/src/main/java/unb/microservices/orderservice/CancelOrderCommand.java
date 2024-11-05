package unb.microservices.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CancelOrderCommand extends Command{

    private RestTemplate restTemplate = new RestTemplate();

    private int orderId;

    @Override
    public void setItem(Product item) {

    }

    @Override
    public void execute() throws StatusChangingException {
        try {
            Command.setCurrentOrder(new Order());
            String url = "http://localhost:8081/cancelOrder?orderId={orderId}";

            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("orderId", orderId);

            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, uriVariables);
        }
        catch(HttpServerErrorException.InternalServerError e){
            throw new StatusChangingException("Unable to cancel order");
        }

    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void undo() throws StatusChangingException, InsufficientFundsException {
        Command placeOrderCommand = new PlaceOrderCommand();
        placeOrderCommand.execute();
    }
}
