package unb.microservices.PCS;

import org.springframework.stereotype.Service;

@Service
public interface Product {
    public int getProduct_ID();

    public String getName();

    public double getCost();

    public int getQuantity();

    public String getCategory();

}
