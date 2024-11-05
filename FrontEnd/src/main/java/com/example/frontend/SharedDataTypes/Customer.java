package com.example.frontend.SharedDataTypes;

public interface Customer {
    void addCustomer(String username, String password);
    public void getCustomer();
    void deleteCustomer(String username, String password);
    void addToOrder(String item);
    void cancelOrder();
    void processPayment(float amount, int cardNumber, int amountInCard);
    // Add additional methods as needed
}
