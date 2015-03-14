/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObj;

import JavaBeans.Order;
import JavaBeans.Orders;
import Utilities.ConnectionFailedException;
import Utilities.DBConnector;
import Utilities.ProductType;
import Utilities.UserSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elizabethdwornik
 */
public class OrdersDAO {

    public ArrayList<String> getInventory(ProductType item) throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        String errString = null;            // String for displaying errors
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
            res = s.executeQuery("SELECT * FROM " + item.getTableName());

            while (res.next()) {
                msgString = res.getString(1) + " : " + res.getString(2)
                        + " : $" + res.getString(4) + " : " + res.getString(3)
                        + " units in stock";
                result.add(msgString);
            } // while
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem getting inventory");
        }

        return result;
    }

    public void submit(Orders orders) throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        Statement s = null;                 // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        int executeUpdateVal;           // Return value from execute indicating effected rows
        
        
        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), "orderinfo");
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }
        try {
        s = DBConn.createStatement();
       

        
            SQLstatement = ("CREATE TABLE " + orders.getOrderTable()
                    + "(item_id int unsigned not null auto_increment primary key, "
                    + "product_id varchar(20), description varchar(80), "
                    + "item_price float(7,2) );");
            executeUpdateVal = s.executeUpdate(SQLstatement);
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem creating order table " + orders.getOrderTable() + ":: " + e);

        } // try
        try {
            SQLstatement = ("INSERT INTO orders (order_date, " + "first_name, "
                    + "last_name, address, phone, total_cost, shipped, "
                    + "ordertable) VALUES ( '" + orders.getOrderDate() + "', "
                    + "'" + orders.getFirstname() + "', " + "'" + orders.getLastname() + "', "
                    + "'" + orders.getAddress() + "', " + "'" + orders.getPhonenumber() + "', "
                    + orders.getCost() + ", " + false + ", '" + orders.getOrderTable() + "' );");

            executeUpdateVal = s.executeUpdate(SQLstatement);

        } catch (Exception e1) {

            try {
                SQLstatement = ("DROP TABLE " + orders.getOrderTable() + ";");
                executeUpdateVal = s.executeUpdate(SQLstatement);

            } catch (Exception e2) {
                throw new ConnectionFailedException("\nProblem deleting unused order table:: "
                        + orders.getOrderTable()+ ":: " + e2);

            } // try
            throw new ConnectionFailedException("\nProblem with inserting into table orders:: " + e1);
        } // try
     
    }
    
    public void addItems(Orders orders, Order order) throws ConnectionFailedException {
        Boolean connectError = false;       // Error flag
        Connection DBConn = null;           // MySQL connection handle
        Statement s = null;                 // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        int executeUpdateVal;           // Return value from execute indicating effected rows   
        
        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), "orderinfo");
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem connecting to database");
        }
        try {
            s = DBConn.createStatement();
            
            SQLstatement = ("INSERT INTO " + orders.getOrderTable()
                    + " (product_id, description, item_price) "
                    + "VALUES ( '" + order.getProductId() + "', " + "'"
                    + order.getDescription() + "', " + order.getItemPrice() + " );");
            executeUpdateVal = s.executeUpdate(SQLstatement);
        } catch (Exception e) {
            throw new ConnectionFailedException("\nProblem with inserting into table " + orders.getOrderTable()
                    + ":: " + e);
        }
    }
}
