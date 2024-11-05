package com.example.databasemanagementsystem;

import SharedDataTypes.Order_Tracking;
import SharedDataTypes.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class order_DB {

    public static int getOrderUser(int order_ID){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        int result = 0;
        ResultSet dbResultSet = null;

        try{
            System.out.println("Retrieving a user's order");
            dbStatement = dbConnection.prepareCall("{CALL getUserOrder(?)}");
            dbStatement.setInt("order_ID", order_ID);
            dbResultSet = dbStatement.executeQuery();
            if(dbResultSet.next()) {
                result = dbResultSet.getInt("user_ID");
            }
            System.out.println("User's Order Retrieved");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }
        return result;
    }

    public static String getOrderStatus(int user_ID){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        String result = null;
        ResultSet dbResultSet = null;

        try{
            System.out.println("Retrieving all user's Orders");
            dbStatement = dbConnection.prepareCall("{CALL getTrackingStatus(?)}");
            dbStatement.setInt("order_ID", user_ID);
            dbResultSet = dbStatement.executeQuery();
            if(dbResultSet.next()) {
                String res = dbResultSet.getString("status");
                result = Order_Tracking.valueOf(res).toString();
            }
            System.out.println("List Retrieved");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }
        System.out.println(result);
        return result;
    }

    public static int addOrder(int user_id){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        int result = 0;
        ResultSet dbResultSet = null;

        try{
            System.out.println("Creating a new Order");
            dbStatement = dbConnection.prepareCall("{CALL addOrder(?)}");
            dbStatement.setInt("user_ID", user_id);
            dbResultSet = dbStatement.executeQuery();
            if(dbResultSet.next()) {
                result = dbResultSet.getInt(1);
            }
            System.out.println("New Order Created");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }
        return result;
    }

    public static void addToOrder(int order_ID, int user_id, Product toInsert){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try{
            System.out.println("Placing Order");
            dbStatement = dbConnection.prepareCall("{CALL addToOrder(?,?,?)}");
            dbStatement.setInt("user_ID", user_id);
            dbStatement.setInt("product_ID", toInsert.getProduct_ID());
            dbStatement.setInt("order_ID", order_ID);
            dbStatement.executeQuery();
            System.out.println("Order Placed");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
        }
    }

    public static void cancelOrder(int order_ID){
        Connection dbConection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try{
            System.out.println("Canceling Order");
            dbStatement = dbConection.prepareCall("{CALL cancelOrder(?,?)}");
            dbStatement.setInt("order_ID", order_ID);
            dbStatement.setInt("user_ID", DB_Connection.UserLoggedIn.getUser_ID());
            dbStatement.executeQuery();
            System.out.println("Order Canceled");
        }
        catch (SQLException e){
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConection);
        }
    }

    public static ArrayList<Map<String, Object>> getUserOrders(){
        Connection dbConection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        ArrayList<Map<String, Object>> result = null;
        ResultSet dbResultSet = null;

        try{
            System.out.println("Retrieving all user's orders");
            dbStatement = dbConection.prepareCall("{CALL getUserOrders(?)}");
            dbStatement.setInt("ID", DB_Connection.UserLoggedIn.getUser_ID());
            dbResultSet = dbStatement.executeQuery();
            result = new ArrayList<>();
            while(dbResultSet.next()){
                Map<String, Object> list = new HashMap<>();
                int ID = dbResultSet.getInt("order_ID");
                String name = dbResultSet.getString("product_name");
                int user_ID = dbResultSet.getInt("user_ID");
                list.put("ID", ID);
                list.put("name", name);
                list.put("user_ID", user_ID);
                result.add(list);
            }
            System.out.println("Orders Retrieved");
        }
        catch (SQLException e){
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConection);
        }
        return result;
    }
}
