package unb.cb.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unb.cb.customerservice.SharedDataTypes.Product;
import unb.cb.customerservice.SharedDataTypes.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService implements Customer {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void addCustomer(String username, String password) {
        String url = "http://localhost:8081/add_user?username={username}&password={password}&type={type}";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("type", "customer");

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);

    }

    public void getCustomer(){
        String url = "http://localhost:8081/getUser";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
    }

    public void sendCustomer(User user){
        String url = "http://localhost:8086/getUser";
        HttpEntity<User> entity = new HttpEntity<>(user);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    }

    @Override
    public void deleteCustomer(String username, String password) {
        String url = "http://localhost:8081/add_user?username={username}&password={password}&type={type}\"";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, params);

    }

    @Override
    public void addToOrder(String item) {

    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public void processPayment(float amount, int cardNumber, int amountInCard) {

    }


}
