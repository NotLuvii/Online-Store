package com.example.frontend.SharedDataTypes;

import java.util.ArrayList;

public class OrderListWrapper {
    private ArrayList<Order> object;

    public OrderListWrapper(){}

    public OrderListWrapper(ArrayList<Order> object){
        this.object = object;
    }

    public ArrayList<Order> getObject() {
        return object;
    }
}