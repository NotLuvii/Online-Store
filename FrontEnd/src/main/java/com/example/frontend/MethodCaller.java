package com.example.frontend;

import com.example.frontend.SharedDataTypes.CustomerService;
import com.example.frontend.SharedDataTypes.ListWrapper;
import com.example.frontend.SharedDataTypes.Product;
import com.example.frontend.SharedDataTypes.ProductPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MethodCaller {

    private RestTemplate restTemplate = new RestTemplate();

    public void addToOrder(Product product){
        String url = "http://localhost:8082/addToOrder?userId={userId}";
        HttpEntity<Product> entity = new HttpEntity<>(product);

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("userId", FrontEndApplication.loggedIn.getUser_ID());

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class, uriVariables);
    }

    public void getUser(){
        String url = "http://localhost:8083/getUser";
        CustomerService customer = restTemplate.getForObject(url, CustomerService.class);
    }

    public void removeFromOrder(Product product){
        String url = "http://localhost:8082/removeFromOrder";
        HttpEntity<Product> entity = new HttpEntity<>(product);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    }

    //Revise this method to send without entity
    public void placeOrder(int cardNumber, double amountInCard){
        try {
            String url = "http://localhost:8082/placeOrder?cardNumber={cardNumber}&amountInCard={amountInCard}";


            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("cardNumber", cardNumber);
            uriVariables.put("amountInCard", amountInCard);

            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, uriVariables);
        }
        catch(Exception e){
            System.out.println("Failed to Place Order, Check Funds");
        }
    }

    public void cancelOrder(int orderId){
        try {
            String url = "http://localhost:8082/cancelOrder?orderId={orderId}";

            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("orderId", orderId);

            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, uriVariables);
        }
        catch(HttpServerErrorException.InternalServerError e){
            System.out.println("Unable to Cancel Order\nOrder is done processing");
        }
    }

    public void undoActionForCart(){
        String url = "http://localhost:8082/undo";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
    }

    public void showCart(){
        String url = "http://localhost:8082/showCart";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        }
        catch(HttpServerErrorException e){
            System.out.println("No Items in cart");
        }
    }

    public void sendUserforAuthority(String username, String password){
        String url = "http://localhost:8081/getUserForAuthority?username={username}&password={password}";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);

        try{
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, requestBody);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addUser(String username, String password){
        String url = "http://localhost:8083/addCustomer?username={username}&password={password}";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    public void getList(String category){
        System.out.println("Sending Request");
        String url = "http://localhost:8080/getCategory?category={category}";

        Map<String, Object> params = new HashMap<>();
        params.put("category", category);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    public void getOrderStatus(int order_ID){
        String url = "http://localhost:8082/getOrderStatus?order_ID={order_ID}";

        Map<String, Object> params = new HashMap<>();
        params.put("order_ID", order_ID);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    public void getUserOrders(){
        String url = "http://localhost:8081/getUserOrders";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
    }

    public void addProduct(String name, double cost, int quantity, String category){
        String url = "http://localhost:8080/addProduct?name={name}&cost={cost}&quantity={quantity}&category={category}";

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("cost", cost);
        params.put("quantity", quantity);
        params.put("category", category);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    public void deleteProduct(int ID){
        String url = "http://localhost:8080/deleteProduct?product_ID={product_ID}";
        Map<String, Object> params = new HashMap<>();
        params.put("product_ID", ID);
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);
    }

    public void updateProduct(Product toUpdate){
        String url = "http://localhost:8080/updateProduct";
        ProductPacket toSend = serialize(toUpdate);
        HttpEntity<ProductPacket> entity = new HttpEntity<>(toSend);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    }

    private ProductPacket serialize (Product toSerialize) {
        return new ProductPacket(toSerialize.getProduct_ID(), toSerialize.getName(), toSerialize.getCost(), toSerialize.getQuantity(), toSerialize.getCategory());
    }

    private Product deSerialize(ProductPacket toDeserialize) {
        return new Product(toDeserialize.product_ID, toDeserialize.name, toDeserialize.cost, toDeserialize.quantity, toDeserialize.type);
    }
}
