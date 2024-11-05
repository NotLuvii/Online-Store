package com.example.frontend.SharedDataTypes;

public class Order {
    private int order_ID;
    private String[] product_name;
    private int user_ID;

    private Order_Tracking status;

    public Order(int order_ID, String[] product_name, int user_ID){
        this.order_ID = order_ID;
        this.product_name = product_name;
        this.user_ID = user_ID;
    }

    public int getOrder_ID() {
        return order_ID;
    }

    public String[] getProduct_name() {
        return product_name;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setStatus(Order_Tracking status){
        this.status = status;
    }
}
