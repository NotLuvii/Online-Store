package com.example.databasemanagementsystem;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import SharedDataTypes.*;

public class user_DB {
    public static void add_user(String username, String password, String type){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try{
            System.out.println("Adding User");
            dbStatement = dbConnection.prepareCall("{CALL add_user(?,?,?)}");
            dbStatement.setString("username", username);
            dbStatement.setString("password", password);
            dbStatement.setString("type", type);
            dbStatement.executeQuery();
            System.out.println("user added");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
        }
    }

    public static void delete_user(String username, String password){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;

        try {
            System.out.println("Deleting User");
            dbStatement = dbConnection.prepareCall("{CALL delete_user(?,?)}");
            dbStatement.setString("username", username);
            dbStatement.setString("password", password);
            dbStatement.executeQuery();
            System.out.println("User Deleted");
        } catch (SQLException e) {
            DB_Connection.getSQLException(e);
        } finally {
            DB_Connection.Closing(dbStatement, dbConnection);
        }
    }

    public static User get_user(int user_ID){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        User result = null;
        try{
            System.out.println("Retrieving User");
            dbStatement = dbConnection.prepareCall("{CALL get_user(?)}");
            dbStatement.setInt("user_ID", user_ID);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                String username = dbResultSet.getString("username");
                String password = dbResultSet.getString("password");
                String type = dbResultSet.getString("type");
                result = new User(user_ID, username, password, type);
            }
            System.out.println("User Retrieved");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
            return null;
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }
        return result;
    }

    public static User login(String username, String password){
        Connection dbConnection = DB_Connection.Connect();
        CallableStatement dbStatement = null;
        ResultSet dbResultSet = null;
        User result = null;
        try{
            System.out.println("Attempting Login");
            dbStatement = dbConnection.prepareCall("{CALL login(?,?)}");
            dbStatement.setString("username", username);
            dbStatement.setString("password", password);
            dbResultSet = dbStatement.executeQuery();
            while(dbResultSet.next()){
                int user_ID = dbResultSet.getInt("user_ID");
                String type = dbResultSet.getString("type");
                result = new User(user_ID, username, password, type);
            }
            System.out.println("Login Successful");
        }
        catch(SQLException e){
            DB_Connection.getSQLException(e);
            return null;
        }
        finally{
            DB_Connection.Closing(dbStatement, dbConnection);
            DB_Connection.ClosingResultSet(dbResultSet);
        }
        return result;
    }
}
