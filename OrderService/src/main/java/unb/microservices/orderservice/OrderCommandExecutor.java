package unb.microservices.orderservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderCommandExecutor {
    private ArrayList<String> log;
    private ArrayList<Command> queue;

    private RestTemplate restTemplate = new RestTemplate();

    private static Order order;

    public void createOrder(int id){
        order = new Order(id, new ArrayList<Product>());
        Command.setCurrentOrder(order);
    }

    public void setOrder(Order orderIn) {
        order = orderIn;
    }

    public void addToOrder(Product item, int id) throws StatusChangingException, InsufficientFundsException {
        if(order == null){
            createOrder(id);
            log = new ArrayList<String>();
            queue = new ArrayList<Command>();
        }

        Command addToOrder = new AddToOrderCommand();
        addToOrder.setItem(item);
        addToOrder.execute();
        log.add("Added " + item.getName());
        queue.add(addToOrder);
    }

    public void removeFromOrder(Product item) throws StatusChangingException, InsufficientFundsException {
        Command removeFromOrder = new RemoveFromOrderCommand();
        removeFromOrder.setItem(item);
        removeFromOrder.execute();
        log.add("Removed " + item.getName());
        queue.add(removeFromOrder);
    }

    public void placeOrder(int cardNumber, double amountInCard) throws InsufficientFundsException {
        PlaceOrderCommand placeOrder = new PlaceOrderCommand();
        placeOrder.setAmountInCard(amountInCard);
        placeOrder.setCardNumber(cardNumber);

        placeOrder.execute();
        log.add("Placed Order ");
        queue.add(placeOrder);
    }

    public void cancelOrder(int orderId) throws StatusChangingException {
        if(log == null){
            log = new ArrayList<String>();
            queue = new ArrayList<Command>();
        }
        CancelOrderCommand cancelOrder = new CancelOrderCommand();
        cancelOrder.setOrderId(orderId);

        cancelOrder.execute();
        log.add("Cancelled Order ");
        queue.add(cancelOrder);
    }

    public void undo() throws StatusChangingException, InsufficientFundsException {
        int len = queue.size();
        log.add("Undid last action");
        queue.get(len-1).undo();
        queue.remove(len-1);
    }

    public void showCart() {
        for (Product item : Command.getOrder().getItems()) {
            System.out.println(item.getName() + " : " + item.getCost());
        }
        String url = "http://localhost:8086/showCart";
        ListWrapper productListWrapper = new ListWrapper(Command.getOrder().getItems());
        // populate productListWrapper with your products
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ListWrapper> entity = new HttpEntity<>(productListWrapper, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

    }

    public void getStatus(int order_ID) {
        String url = "http://localhost:8081/getOrderStatus?order_ID={order_ID}";
        Map<String, Object> params = new HashMap<>();
        params.put("order_ID", order_ID);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    public void sendStatus(String status) {
        String url = "http://localhost:8086/getOrderStatus?status={status}";
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }
}
