package unb.microservices.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlaceOrderCommand extends Command{

    private RestTemplate restTemplate = new RestTemplate();
    private int cardNumber;
    private double amountInCard;

    @Override
    public void setItem(Product item) {

    }

    @Override
    public void execute() throws InsufficientFundsException {
        try {
            String url = "http://localhost:8085/pay?userId={userId}&cardNumber={cardNumber}&amountInCard={amountInCard}";
            // populate productListWrapper with your products
            ListWrapper wrapper = new ListWrapper(Command.getOrder().getItems());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ListWrapper> entity = new HttpEntity<>(wrapper);


            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("userId", Command.getOrder().getUserId());
            uriVariables.put("cardNumber", cardNumber);
            uriVariables.put("amountInCard", amountInCard);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class, uriVariables);

            Command.setCurrentOrder(null);
            OrderCommandExecutor OCE = new OrderCommandExecutor();
            OCE.setOrder(null);
        }
        catch(Exception e){
            throw new InsufficientFundsException("Payment Failed");
        }
    }

    public void setCardNumber(int cardNumber){
        this.cardNumber = cardNumber;
    }

    public void setAmountInCard(double amountInCard){
        this.amountInCard = amountInCard;
    }



    @Override
    public void undo() throws InsufficientFundsException, StatusChangingException {
        Command cancelCommand = new CancelOrderCommand();
        cancelCommand.execute();
    }
}
