/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObj;

import JavaBeans.Orders;
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
 * @author elizabethdwornik
 */
public class ShippingDAO {
    
    public ArrayList<String> getInventory(Orders orders) throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        String errString = null;            // String for displaying errors
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        String SQLStatement;                // SQL query
        String orderTable = null;           // The name of the table containing the order items
        
        ArrayList<String> result = new ArrayList<String>();
                   
            try {

                DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(),"orderinfo");
                
            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem connecting to database");
            }
            
            try {
                s = DBConn.createStatement();
                SQLStatement = "SELECT * FROM orders WHERE order_id = " + Integer.parseInt(orders.getOrderTable());
                res = s.executeQuery(SQLStatement);              
                  
                
                while (res.next()) {

                    orderTable = res.getString(9);         // name of table with list of items
                    result.add(res.getString(3));
                    result.add(res.getString(4));
                    result.add(res.getString(6));
                    result.add(res.getString(2));
                    result.add(res.getString(5));
                    
                } // for each element in the return SQL query
               
            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem getting inventory");
            }
            
            try{
            // get the order items from the related order table
                SQLStatement = "SELECT * FROM " + orderTable;
                res = s.executeQuery(SQLStatement);

                while (res.next()) {
                    msgString = res.getString(1) + ":  PRODUCT ID: " + res.getString(2)
                            + "  DESCRIPTION: " + res.getString(3) + "  PRICE $" + res.getString(4);
                    result.add(msgString);

                } // while

            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem getting order items:: " + e);
            } // end try-catch
        
        return result;
    }
    public int setShippingStatus(Orders orders) throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        String errString = null;            // String for displaying errors
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        String SQLStatement;                // SQL query
        String orderTable = null;           // The name of the table containing the order items
        int rows;                           // Rows updated
        
        try {

               DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(),"orderinfo");
                
            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem connecting to database");
            }
        try{
          s = DBConn.createStatement();
          SQLStatement = "UPDATE orders SET shipped=" + true + " WHERE order_id=" + orders.getOrderID();
          
          // execute the statement
                rows = s.executeUpdate(SQLStatement);
         } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem updating status:: " + e);    
         } // end try-catch
            return rows; 
   }
    
   public ArrayList<String> getPendingOrders() throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        String errString = null;            // String for displaying errors
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        String SQLStatement;                // SQL query
        String orderTable = null;           // The name of the table containing the order items 
        int shippedStatus;                  // if 0, order not shipped, if 1 order shipped
        ArrayList<String> result = new ArrayList<String>();
       
       try {

               DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(),"orderinfo");
                
            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem connecting to database");
            }
       try {
                // Create a query to get all the orders and execute the query
                s = DBConn.createStatement();
                res = s.executeQuery("Select * from orders");
                
                 while (res.next()) {
                    shippedStatus = Integer.parseInt(res.getString(8));

                    if (shippedStatus == 0) {
                        msgString = "ORDER # " + res.getString(1) + " : " + res.getString(2)
                                + " : " + res.getString(3) + " : " + res.getString(4);
                  
                        result.add(msgString);
                    } // shipped status check

                } // while
        } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem getting inventory:: " + e);
        }
       return result;
   }

    
   public ArrayList<String> getShippedOrders() throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        String errString = null;            // String for displaying errors
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        Statement s = null;                 // SQL statement pointer
        String SQLStatement;                // SQL query
        String orderTable = null;           // The name of the table containing the order items 
        int shippedStatus;                  // if 0, order not shipped, if 1 order shipped
        ArrayList<String> result = new ArrayList<String>();
       
       try {

               DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(),"orderinfo");
                
            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem connecting to database");
            }
       try {
                // Create a query to get all the orders and execute the query
                s = DBConn.createStatement();
                res = s.executeQuery("Select * from orders");
                
                 while (res.next()) {
                    shippedStatus = Integer.parseInt(res.getString(8));

                    if (shippedStatus == 1) {
                        msgString = "ORDER # " + res.getString(1) + " : " + res.getString(2)
                                + " : " + res.getString(3) + " : " + res.getString(4);
                  
                        result.add(msgString);
                    } // shipped status check

                } // while
        } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem getting inventory:: " + e);
        }
       return result;
   }
}
