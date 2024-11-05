package com.example.paymentservice;

import java.util.ArrayList;

public class Payment {
    private int userId;
    private ArrayList<Product> items;
    private int cardNumber;
    private double amountInCard;
    private PaymentState state;

    public Payment(int userId, ArrayList<Product> items,int cardNumber, double amountInCard) {
        this.userId = userId;
        this.items = items;
        this.cardNumber = cardNumber;
        this.amountInCard =amountInCard;
        state =  new IntializedState();
    }

    public void changeState(PaymentState state){
        this.state = state;
    }

    public void proccess() throws InsufficientFundsException {
        state.proccess(this);
    }

    public double getAmountInCard() {
        return amountInCard;
    }

    public float getAmount() {
        float amount = 0;
        for (Product item: items) {
            amount += item.getCost();
        }
        return amount;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public int getUserId() {
        return userId;
    }

    public int getCardNumber() {
        return cardNumber;
    }
}
