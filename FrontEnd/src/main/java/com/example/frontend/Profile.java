package com.example.frontend;

import com.example.frontend.SharedDataTypes.Product;
import com.example.frontend.SharedDataTypes.User;

public class Profile {
    public void getProfile(User user){
        FrontEndApplication.loggedIn = user;
        System.out.println("-------------------------------");
        System.out.printf("|\t%-20s\t  |\n", "Profile Information");
        System.out.println("-------------------------------");
        System.out.printf("|\t%-10s\t:\t%-10s|\n", "Username", user.getUsername());
        System.out.printf("|\t%-10s\t:\t%-10s|\n", "Password", user.getPassword());
        System.out.printf("|\t%-10s\t:\t%-10s|\n", "user ID", user.getUser_ID());
        System.out.printf("|\t%-10s\t:\t%-10s|\n", "User Type", user.getUserType());
        System.out.println("-------------------------------");
    }
}
