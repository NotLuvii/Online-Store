package com.example.databasemanagementsystem;

import SharedDataTypes.Order;
import SharedDataTypes.Product;
import SharedDataTypes.User;

import java.util.ArrayList;

public interface Database {

    /**
     * Add user to database
     * @param username the user's username
     * @param password the user's password
     * @param type the user's type
     */
    void add_user(String username, String password, String type);


    /**
     * delete a user from the database
     * For security reasons, only the user themselves can delete their profile
     * @param username the user's username
     * @param password the user's password
     */
    void delete_user(String username, String password) throws UnauthorizedAccessException;
    User get_User(int user_ID);

    /**
     * Save the logged-in user until the server is stopped
     * @param username the user that is logged in
     * @param password the user's password that logged in
     */
    void getUserForAuthority(String username, String password);

    int sendAuthority();

    /**
     * Add a product to the database
     * For security reasons, only a retailer can add a product for sale
     * @param name the name of the product
     * @param cost the cost of the product
     * @param quantity the quantity of the product
     * @param category the category of the product
     * @throws UnauthorizedAccessException Throw the exception if anyone other thant a retailer is attempting to add a product
     */
    void add_product(String name, double cost, int quantity, String category) throws UnauthorizedAccessException;

    Product get_product(int product_ID);

    void update_product(Product toUpdate) throws UnauthorizedAccessException; //not implemented

    Product delete_product(int product_ID) throws UnauthorizedAccessException; //not implemented

    ArrayList<Product> getCategory(String category);

    void setProductQuantity(int product_ID, int quantity) throws UnauthorizedAccessException;

    void placeOrder(int order_ID, ArrayList<Product> order);

    void cancelOrder(int order_ID) throws UnauthorizedAccessException, StatusChangingException;

    void getOrderStatus(int order_ID);

    ArrayList<Order> getUserOrder();
}
