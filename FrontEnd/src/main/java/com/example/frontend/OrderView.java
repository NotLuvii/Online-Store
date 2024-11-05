package com.example.frontend;

import java.util.ArrayList;
import java.util.Scanner;

import com.example.frontend.SharedDataTypes.*;
public class OrderView {
    View view = new View();
    MethodCaller caller = new MethodCaller();
    Scanner scan = new Scanner(System.in);
    public void getOrderStatus(String status){
        System.out.println("----------------");
        System.out.println("Order number: " + View.order_ID);
        System.out.println("Order Status: " + status);
        while(true){
            System.out.println("To cancel an order, Enter \"cancel\" or to return to Menu, enter \"Menu\"");
            String input = scan.nextLine();
            if(input.equals("cancel")){
                System.out.println("Order ID: " + View.order_ID);
                caller.cancelOrder(View.order_ID);
                view.CustomerView();
            } else if(input.equals("menu")){
                view.CustomerView();
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    public void displayOrders(ArrayList<Order> list){
        for(Order order: list) {
            System.out.println("--------------");
            System.out.println("Order ID: " + order.getOrder_ID());
            for(String prod: order.getProduct_name()){
                System.out.println(prod);
            }
        }
        System.out.print("Enter your order ID: ");
        View.order_ID = scan.nextInt();
        scan.nextLine();
        System.out.println("order ID: (I'm in view line 34) " + View.order_ID);
        caller.getOrderStatus(View.order_ID);
    }
}
