package com.example.frontend;

import com.example.frontend.SharedDataTypes.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class Categories {
    MethodCaller MC = new MethodCaller();
    public void getList(ArrayList<Product> list) {
        System.out.println("In Get List");
        printList(list);
        String input = "";
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("To Add a Product to your Cart, Enter \"add product\", or Enter \"Menu\" to return to Menu");
            input = scan.nextLine().toLowerCase();
            if(input.equals("add product")){
                System.out.print("Enter product ID: ");
                int ID = scan.nextInt();
                scan.nextLine();
                for(Product temp : list){
                    if (temp.getProduct_ID() == ID) {
                        MC.addToOrder(temp);
                        System.out.println("Added to cart");
                        break;
                    }
                }

            } else if((input.equals("menu"))){
                View view = new View();
                view.CustomerView();
            } else {
                System.out.println("Incorrect Command");
            }

        }

    }

    public void getEmployeeList() {
        MethodCaller caller = new MethodCaller();
        String input = "";
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("To Add a product, Enter \"Add\"\n" +
                    "To remove a product, Enter \"Remove\"\n" +
                    "To update a product, Enter \"Update\"\n" +
                    "To view a list of Products from a specific Category, Enter \"category\"\n" +
                    "To Return to Menu, Enter \"Menu\"");
            input = scan.nextLine().toLowerCase();
            switch(input){
                case "add":
                    System.out.println("Fill in the information for the new product");
                    System.out.print("Product Name: ");
                    String name = scan.nextLine();
                    System.out.print("Product Cost: ");
                    double cost = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Product Quantity: ");
                    int quantity = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Product Category: ");
                    String category = scan.nextLine();
                    caller.addProduct(name, cost, quantity, category);
                    break;
                case "remove":
                    System.out.println("Enter the Product ID you would like to remove");
                    System.out.println("Product ID: ");
                    int product_ID = scan.nextInt();
                    scan.nextLine();
                    caller.deleteProduct(product_ID);
                    break;
                case "update":
                    System.out.println("Enter the Product ID you would like to update");
                    int ID = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter the product's updated name, or X to skip");
                    String updatedName = scan.nextLine();
                    System.out.println("Enter the product's updated cost, or -1 to skip");
                    double updatedCost = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Enter the product's updated quantity, or -1 to skip");
                    int updatedQuantity = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter the product's updated category name, or X to skip");
                    String updatedCategory = scan.nextLine();
                    Product toUpdate = new Product(ID, updatedName, updatedCost, updatedQuantity, updatedCategory);
                    caller.updateProduct(toUpdate);
                    System.out.println("Product Successfully Updated");
                    break;
                case "category":
                    System.out.println("-------------------------");
                    System.out.println("|\tDairy\t|\tMeat\t|");
                    System.out.println("-------------------------");
                    System.out.println("Select a Category to View The Products or Enter Return to Return to Menu");
                    String categ = scan.nextLine().toLowerCase();
                    if(categ.equals("dairy") || categ.equals("meat")){
                        caller.getList(categ);
                    } else {
                        System.out.println("Invalid category");
                    }
                case "menu":
                    View view = new View();
                    view.RetailerView();
            }
        }

    }

    public void printList(ArrayList<Product> list){
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("|\t%-20s %-20s %-20s %-20s|\n", "Product ID", "Product Name", "Product Cost", "Product Quantity");
        System.out.println("----------------------------------------------------------------------------------------");

        for(Product temp : list){
            System.out.printf("|\t%-20d %-20s %-20.2f %-20d|\n", temp.getProduct_ID(), temp.getName(), temp.getCost(), temp.getQuantity());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
