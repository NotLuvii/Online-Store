package unb.microservices.orderservice;

import java.util.ArrayList;

public class Order {
    private int userId;
    private ArrayList<Product> items;

    public Order(int id, ArrayList<Product> itm){
        userId = id;
        items = itm;
    }

    public Order() {

    }

    public int getUserId() {
        return userId;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }
}
