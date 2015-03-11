/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderApp;

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
                DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(),item.getDatabaseName());                
            } catch (Exception e) {
                throw new ConnectionFailedException("\nProblem connecting to database");
            }
            
            try {
                s = DBConn.createStatement();
                res = s.executeQuery("select * from " + item.getTableName());              
                              
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
}
