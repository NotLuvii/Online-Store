package unb.microservices.orderservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class OrderCommandExecutorController {
    private OrderCommandExecutor executor = new OrderCommandExecutor();
    private RestTemplate restTemplate;

    @PostMapping("/addToOrder")
    public void add(@RequestParam("userId") int userId, @RequestBody Product item) throws StatusChangingException, InsufficientFundsException {
        System.out.println("added to order");
        executor.addToOrder(item, userId);
    }

    @PostMapping("/removeFromOrder")
    public void remove(@RequestBody Product item) throws StatusChangingException, InsufficientFundsException {
        System.out.println("removed from order");
        executor.removeFromOrder(item);
    }

    @PostMapping("/placeOrder")
    public void place(@RequestParam("cardNumber") int cardNumber, @RequestParam("amountInCard") double amountInCard) throws InsufficientFundsException {
        System.out.println("placed order");
        executor.placeOrder(cardNumber, amountInCard);
    }
    @PostMapping("/cancelOrder")
    public void cancel(@RequestParam("orderId") int orderId) throws StatusChangingException {
        System.out.println("cancelled order");
        executor.cancelOrder(orderId);
    }

    @PostMapping("/getOrderStatus")
    public void getOrderStatus(@RequestParam("order_ID") int order_ID){
        System.out.println("Getting order status");
        executor.getStatus(order_ID);
    }

    @PostMapping("/sendStatus")
    public void sendStatus(@RequestParam("status") String status){
        System.out.println("Getting order status");
        executor.sendStatus(status);
    }

    @PostMapping("/undo")
    public void undo() throws StatusChangingException, InsufficientFundsException {
        System.out.println("undo");
        executor.undo();
    }

    @PostMapping("/showCart")
    public void showCart(){
        System.out.println("Show cart");
        executor.showCart();
    }
}
