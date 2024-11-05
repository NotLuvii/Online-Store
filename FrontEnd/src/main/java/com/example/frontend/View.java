package com.example.frontend;

import java.util.Properties;
import java.util.Scanner;

public class View {

    MethodCaller caller = new MethodCaller();
    Scanner scan = new Scanner(System.in);
    static int order_ID;
    public void CustomerView(){

        while(true){
            System.out.println("Home\nEnter One of the Following Options" +
                    "\n---------------------------------------------" +
                    "\nCart       -     to view your cart" +
                    "\nOrder      -     to view an order status" +
                    "\nCategories -     to view all categories" +
                    "\nProfile    -     to view your Profile" +
                    "\nLogout     -     to Logout of the application" +
                    "\nExit       -     to Exit the application" +
                    "\n---------------------------------------------");
            String in = scan.nextLine().toLowerCase();
            switch(in){
                case "cart":
                    caller.showCart();
                    break;

                case "order":
                    //get all orders for a user first
                    caller.getUserOrders();
                    break;

                case "categories":
                    String cat = "";
                    while(true){
                        System.out.println("-------------------------");
                        System.out.println("|\tDairy\t|\tMeat\t|");
                        System.out.println("-------------------------");
                        System.out.println("Select a Category to View The Products or Enter Return to Return to Menu");
                        cat = scan.nextLine().toLowerCase();
                        System.out.println("Scanned");
                        if(cat.equals("dairy") || cat.equals("meat")){
                            System.out.println("Getting list");
                            caller.getList(cat);

                        } else if(cat.equals("menu")){
                            CustomerView();
                        }
                        else {
                            System.out.println("Invalid category");
                        }
                    }

                case "profile":
                    Profile profile = new Profile();
                    profile.getProfile(FrontEndApplication.loggedIn);
                    break;

                case "exit":
                    System.exit(1);
                    break;

                case "logout":
                    //Logout, change userLoggedIn = null in DB
                    Login login = new Login();
                    login.login();

                default:
                    System.out.println("Unknown Command");
            }
        }
    }

    public void RetailerView(){
        while(true) {
            System.out.println("Home\n - Enter" +
                    "\nCategories - to view all categories" +
                    "\nProfile - to view your profile" +
                    "\nLogout  - to Logout of the application" +
                    "\nExit - To Exit the application");
            String input = scan.nextLine().toLowerCase();
            switch(input){
                case "categories":
                    Categories cat = new Categories();
                    cat.getEmployeeList();
                    break;

                case "profile":
                    Profile prof = new Profile();
                    prof.getProfile(FrontEndApplication.loggedIn);
                    break;

                case "logout":
                    Login log = new Login();
                    log.login();
                    break;

                case "exit":
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
}
