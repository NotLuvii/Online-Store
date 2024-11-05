package com.example.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompleteState implements PaymentState{

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void proccess(Payment payment) {
        String url = "http://localhost:8081/placeOrder?user_ID={user_ID}";
        ListWrapper productListWrapper = new ListWrapper(payment.getItems());
        // populate productListWrapper with your products
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ListWrapper> entity = new HttpEntity<>(productListWrapper, headers);

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("user_ID", payment.getUserId());

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class, uriVariables);
        System.out.println("Payment Complete");
        //add database method
    }
}
