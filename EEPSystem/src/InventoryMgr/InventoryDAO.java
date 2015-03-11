/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryMgr;

import JavaBeans.Product;
import Utilities.ConnectionFailedException;
import Utilities.DBConnector;
import Utilities.ProductType;
import Utilities.UserSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author songxiong
 */
public class InventoryDAO {

    public ArrayList<String> getInventory(ProductType item) throws ConnectionFailedException {

        Connection DBConn = null;           // MySQL connection handle
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        ArrayList<String> result = new ArrayList<String>();

        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), item.getDatabaseName());            
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }

        try {
            s = DBConn.createStatement();
            res = s.executeQuery("select * from " + item.getTableName());

            while (res.next()) {
                msgString = item.getTableName() + ">>" + res.getString(1) + "::" + res.getString(2)
                            + " :: " + res.getString(3) + "::" + res.getString(4);
                result.add(msgString);
            } // while
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem getting inventory");
        }

        return result;
    }
    
    public void addProduct(ProductType type, Product item) throws ConnectionFailedException {

        Connection DBConn = null;           // MySQL connection handle
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        int executeUpdateVal = 0;

        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), type.getDatabaseName());            
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }
        
        try {
            s = DBConn.createStatement();
            if (type.getDatabaseName().equals("inventory")) {
                SQLstatement = ("INSERT INTO " + type.getTableName() + " (product_code, "
                            + "description, quantity, price) VALUES ( '"
                            + item.getProductCode() + "', " + "'" + item.getDescription() + "', "
                            + item.getQuantity() + ", " + item.getPrice() + ");");
            } else {
                SQLstatement = ("INSERT INTO " + type.getTableName() + " (productid, "
                            + "productdescription, productquantity, productprice) VALUES ( '"
                            + item.getProductCode() + "', " + "'" + item.getDescription() + "', "
                            + item.getQuantity() + ", " + item.getPrice() + ");");
            }
            executeUpdateVal = s.executeUpdate(SQLstatement); 
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }
    }
    
    public int deleteItem(ProductType type, String productID) throws ConnectionFailedException {
        Connection DBConn = null;           // MySQL connection handle
        int executeUpdateVal;               // Return value from execute indicating effected rows
        java.sql.Statement s = null;        // SQL statement pointer
        String SQLstatement = null;         // String for building SQL queries
        
        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), type.getDatabaseName());
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }
        
        try {
            s = DBConn.createStatement();
            SQLstatement = ("DELETE FROM " + type.getTableName() + " WHERE product_code = '" + productID + "';");

            executeUpdateVal = s.executeUpdate(SQLstatement);
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem with delete");
        }
        return executeUpdateVal;
    }
    
    public int decrementItem(ProductType type, String productID) throws ConnectionFailedException {
        Connection DBConn = null;           // MySQL connection handle
        int executeUpdateVal;               // Return value from execute indicating effected rows
        java.sql.Statement s = null;        // SQL statement pointer
        String SQLstatement = null;         // String for building SQL queries
        
        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), type.getDatabaseName());
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }
        
        try {
            s = DBConn.createStatement();
            if (type.getDatabaseName().equals("inventory")) {
                SQLstatement = ("UPDATE " + type.getTableName() + " set quantity=(quantity-1) where product_code = '" + productID + "';");
            } else {
                SQLstatement = ("UPDATE " + type.getTableName() + " set productquantity=(productquantity-1) where productid = '" + productID + "';");
            }
            executeUpdateVal = s.executeUpdate(SQLstatement);
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem with decrement");
        }
        return executeUpdateVal;
    }
    
    public ArrayList<String> getItem(ProductType type, String productID) throws ConnectionFailedException {

        Connection DBConn = null;           // MySQL connection handle
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        ArrayList<String> result = new ArrayList<String>();

        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), type.getDatabaseName());            
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }

        try {
            s = DBConn.createStatement();
            if (type.getDatabaseName().equals("inventory")) {
                res = s.executeQuery("select * from " + type.getTableName() + " where product_code = '" + productID + "';");
            } else {
                res = s.executeQuery("select * from " + type.getTableName() + " where productid = '" + productID + "';");
            }
            while (res.next()) {
                msgString = type.getTableName() + ">>" + res.getString(1) + "::" + res.getString(2)
                            + " :: " + res.getString(3) + "::" + res.getString(4);
                result.add(msgString);
            } // while
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem getting product");
        }

        return result;
    }
}
