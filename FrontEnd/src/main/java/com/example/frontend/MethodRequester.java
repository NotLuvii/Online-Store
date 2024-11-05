package com.example.frontend;

import com.example.frontend.SharedDataTypes.ListWrapper;
import com.example.frontend.SharedDataTypes.*;
import com.example.frontend.SharedDataTypes.Product;
import com.example.frontend.SharedDataTypes.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodRequester {
    @PostMapping("/getAuthority")
    public void getAuthority(@RequestParam("in") int in){
        Login.setAuthority(in);
    }
    @PostMapping("/showCart")
    public void showCart(@RequestBody ListWrapper cart){

        Cart displayer = new Cart();
        displayer.displayCart(cart.getObject());
    }

    @PostMapping("/sendCategory")
    public void receiveCategory(@RequestBody ListWrapper productList){
        System.out.println("Received");
        Categories cat = new Categories();
        if(FrontEndApplication.loggedIn.getUserType().equals("customer")) {
            System.out.println("Received category");
            cat.getList(productList.getObject());
        } else {
            cat.printList(productList.getObject());
        }
    }

    @PostMapping("/getUser")
    public void getUser(@RequestBody User user){
        Profile prof = new Profile();
        FrontEndApplication.loggedIn = user;
        System.out.println("Welcome Back " + user.getUsername());

    }

    @PostMapping("/getOrderStatus")
    public void getOrderStatus(@RequestParam("status") String orderStatus){
        OrderView order = new OrderView();
        order.getOrderStatus(orderStatus);
    }

    @PostMapping("/sendUserOrders")
    public void getUserOrders(@RequestBody OrderListWrapper list){
        OrderView view = new OrderView();
        view.displayOrders(list.getObject());
    }
}
