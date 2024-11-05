package com.example.databasemanagementsystem;

import SharedDataTypes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBAccess implements Database{

    @Override
    public void add_user(String username, String password, String type) {
        user_DB.add_user(username, password, type);
    }

    @Override
    public void delete_user(String username, String password) throws UnauthorizedAccessException {
        if(DB_Connection.getAuthority("customer") == 200 &&
                DB_Connection.UserLoggedIn.getUsername().equals(username) &&
                DB_Connection.UserLoggedIn.getPassword().equals(password)) {
            user_DB.delete_user(username, password);
        } else {
            throw new UnauthorizedAccessException("Unauthorized access to delete user");
        }
    }

    @Override
    public User get_User(int user_ID) {
        return user_DB.get_user(user_ID);
    }

    @Override
    public void getUserForAuthority(String username, String password) {
        DB_Connection.setAuthority(username, password);
    }

    @Override
    public int sendAuthority() {
        if(DB_Connection.UserLoggedIn.getUserType().equals("customer")){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void add_product(String name, double cost, int quantity, String category) throws UnauthorizedAccessException {
        if(DB_Connection.getAuthority("retailer") == 200) {
            product_DB.add_product(name, cost, quantity, category);
        } else {
            throw new UnauthorizedAccessException("Unauthorized Access for adding product");
        }
    }

    @Override
    public Product get_product(int product_ID){
        return product_DB.get_product(product_ID);
    }

    @Override
    public void update_product(Product toUpdate) throws UnauthorizedAccessException {
        if(DB_Connection.getAuthority("retailer") == 200) {
            Product test = product_DB.get_product(toUpdate.getProduct_ID());
            System.out.println(test);
            if(toUpdate.getName().equals("X")){
                toUpdate.setName(test.getName());
            }
            if(toUpdate.getCost() == -1){
                toUpdate.setCost(test.getCost());
            }
            if(toUpdate.getCategory().equals("X")){
                toUpdate.setCategory(test.getCategory());
            }
            if(toUpdate.getQuantity() == -1){
                toUpdate.setQuantity(test.getQuantity());
            }
            product_DB.updateProduct(toUpdate);
        } else {
            throw new UnauthorizedAccessException("Unauthorized access to update product");
        }
    }

    @Override
    public Product delete_product(int product_ID) throws UnauthorizedAccessException {
        if (DB_Connection.getAuthority("retailer") == 200) {
            return product_DB.deleteProduct(product_ID);
        } else {
            throw new UnauthorizedAccessException("Unauthorized access to delete product");
        }
    }

    @Override
    public ArrayList<Product> getCategory(String category) {
        return product_DB.getCategory(category);
    }

    @Override
    public void setProductQuantity(int product_ID, int quantity) throws UnauthorizedAccessException {
        if(DB_Connection.getAuthority("retailer") == 200) {
            product_DB.setProductQuantity(product_ID, quantity);
        } else {
            throw new UnauthorizedAccessException("Unauthorized access to set quantity");
        }
    }

    @Override
    public void placeOrder(int user_ID, ArrayList<Product> order) {
        if(user_ID == DB_Connection.UserLoggedIn.getUser_ID()) {
            int order_ID = order_DB.addOrder(user_ID);
            for (Product x : order) {
                order_DB.addToOrder(order_ID, user_ID, x);
            }
        }
    }

    @Override
    public void cancelOrder(int order_ID) throws UnauthorizedAccessException, StatusChangingException {
        int user = order_DB.getOrderUser(order_ID);
        if (user == DB_Connection.UserLoggedIn.getUser_ID()) {
            String toCheck = order_DB.getOrderStatus(order_ID);
            System.out.println("In cancel Order");
            if(toCheck.equals("NotPlaced") || toCheck.equals("Processing")){
                order_DB.cancelOrder(order_ID);
            } else {
                throw new StatusChangingException("Unable to Change Status, Order is already " + toCheck);
            }
        } else {
            throw new UnauthorizedAccessException("Unauthorized access to cancel order");
        }
    }

    @Override
    public void getOrderStatus(int order_ID) {
        String status = order_DB.getOrderStatus(order_ID);
        MethodSender sender = new MethodSender();
        System.out.println("Sending status");
        sender.sendStatus(status);
    }

    @Override
    public ArrayList<Order> getUserOrder() {
        ArrayList<Map<String, Object>> result = order_DB.getUserOrders();
        ArrayList<Order> exit = new ArrayList<>();
        Map<Integer, ArrayList<String>> orderProductsMap = new HashMap<>();

        for (Map<String, Object> row : result) {
            int orderID = (int) row.get("ID");
            String productName = (String) row.get("name");

            if (!orderProductsMap.containsKey(orderID)) {
                orderProductsMap.put(orderID, new ArrayList<>());
            }

            orderProductsMap.get(orderID).add(productName);
        }

        for (Map.Entry<Integer, ArrayList<String>> entry : orderProductsMap.entrySet()) {
            int orderID = entry.getKey();
            String[] productNames = entry.getValue().toArray(new String[0]);
            Order order = new Order(orderID, productNames, DB_Connection.UserLoggedIn.getUser_ID());
            exit.add(order);
        }

        return exit;
    }

}
