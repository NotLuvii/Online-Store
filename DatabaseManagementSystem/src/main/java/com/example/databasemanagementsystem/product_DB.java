package com.example.databasemanagementsystem;

import SharedDataTypes.Product;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

public class product_DB {
    public static void add_product(String product_name, double product_price, int quantity, String category){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try {
            System.out.println("Adding Product");
            dbStatement = dbConnection.prepareCall("{CALL addProduct(?,?,?,?)}");
            dbStatement.setString("product_name", product_name);
            dbStatement.setDouble("product_price", product_price);
            dbStatement.setInt("product_quantity", quantity);
            dbStatement.setString("product_category", category);
            dbStatement.executeQuery();
            System.out.println("Product Added");
        } catch (SQLException e) {
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConnection);
        }
    }

    public static Product get_product(int product_ID){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        Product result = null;

        try{
            System.out.println("Retrieving Product");
            dbStatement = dbConnection.prepareCall("{CALL getProduct(?)}");
            dbStatement.setInt("ID", product_ID);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String name = dbResultSet.getString("product_name");
                double cost = dbResultSet.getDouble("product_cost");
                int quantity = dbResultSet.getInt("product_quantity");
                String category = dbResultSet.getString("product_category");
                result = new Product(product_ID, name, cost, quantity, category);
            }
            System.out.println("Product Retrieved");
        } catch(SQLException e){
            DB_Connection.getSQLException(e);
        }
        finally {
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }

        return result;
    }

    //not done
    public static ArrayList<Product> getCategory(String category){
        System.out.println("In method");
        ArrayList<Product> result = null;
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet= null;
        try{
            System.out.println("Retrieving a list of products in " + category);
            dbStatement = dbConnection.prepareCall("{CALL getCategoryProducts(?)}");
            dbStatement.setString(1, category);
            dbResultSet = dbStatement.executeQuery();
            result = new ArrayList<>();
            while(dbResultSet.next()){
                int id = dbResultSet.getInt("product_ID");
                String name = dbResultSet.getString("product_name");
                double cost = dbResultSet.getDouble("product_cost");
                int quantity = dbResultSet.getInt("product_quantity");
                String cat = dbResultSet.getString("product_category");
                Product temp = new Product(id, name, cost, quantity, cat);
                result.add(temp);
            }
            System.out.println("List retrieved");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }
        return result;
    }

    public static void setProductQuantity(int product_ID, int quantity){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try {
            System.out.println("Setting Product Quantity");
            dbStatement = dbConnection.prepareCall("{CALL update_quantity(?,?)}");
            dbStatement.setInt("product_ID", product_ID);
            dbStatement.setInt("product_quantity", quantity);
            dbStatement.executeQuery();
            System.out.println("Product Quantity Updated");
        } catch (SQLException e) {
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConnection);
        }
    }

    public static void updateProduct(Product toUpdate){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try {
            System.out.println("Updating Product");
            dbStatement = dbConnection.prepareCall("{CALL updateProduct(?,?,?,?,?)}");
            dbStatement.setInt("ID", toUpdate.getProduct_ID());
            dbStatement.setString("product_name", toUpdate.getName());
            dbStatement.setDouble("product_cost", toUpdate.getCost());
            dbStatement.setInt("product_quantity", toUpdate.getQuantity());
            dbStatement.setString("product_category", toUpdate.getCategory());
            dbStatement.executeQuery();
            System.out.println("Product Updated");
        } catch (SQLException e) {
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConnection);
        }
    }

    public static Product deleteProduct(int product_ID){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        Product result = null;
        ResultSet dbResultSet = null;
        try {
            System.out.println("Deleting Product");
            dbStatement = dbConnection.prepareCall("{CALL deleteProduct(?)}");
            dbStatement.setInt("ID", product_ID);
            dbResultSet = dbStatement.executeQuery();
            if (dbResultSet.next()) {
                int id = dbResultSet.getInt("Product_ID");
                String name = dbResultSet.getString("product_name");
                double cost = dbResultSet.getDouble("product_cost");
                int quantity = dbResultSet.getInt("product_quantity");
                String category = dbResultSet.getString("product_category");
                result = new Product(id, name, cost, quantity, category);
            }
            System.out.println("Product Deleted");
        } catch (SQLException e) {
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConnection);
        }
        return result;
    }
}
