package com.example.frontend;

import com.example.frontend.SharedDataTypes.User;

import java.util.Scanner;

public class Login {
    static int authority = 0;
    MethodCaller name = new MethodCaller();

    static String username = "";
    static String password = "";
    public void login(){

        System.out.println("-----Welcome to WebBazaar-----");
        Scanner scan = new Scanner(System.in);
        boolean loggedIn = false;
        User userIn = null;
        while(!loggedIn) {
            System.out.println("Login or Signup");
            String in = scan.nextLine();
            if (in.equals("Signup")) {
                System.out.print("Enter a new username: ");
                username = scan.nextLine();
                System.out.print("Enter desired password: ");
                password = scan.nextLine();
                name.addUser(username, password);
                loggedIn = true;
            } else if (in.equals("Login")) {
                System.out.print("username: ");
                username = scan.nextLine();
                System.out.print("password: ");
                password = scan.nextLine();
                //check if user is valid + return User
                loggedIn = true;
            } else {
                System.out.println("Incorrect Command");
            }
        }


        name.sendUserforAuthority(username, password);

        View view = new View();
        name.getUser();

        if(authority == 1){
            view.CustomerView();
        } else {
            view.RetailerView();
        }



    }

    public static void setAuthority(int in){
        authority = in;
    }
}