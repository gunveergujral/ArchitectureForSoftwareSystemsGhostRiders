/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMgr;

import Utilities.ConnectionFailedException;
import Utilities.DBConnector;
import Utilities.UserSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author songxiong
 */
public class ActivityDAO {

    public int setLoginTime(int userID, String dateTimeStamp) {
        Connection DBConn = null;       // MySQL connection handle
        Statement s = null;             // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        Boolean executeError = false;   // Error flag
        String errString = null;        // String for displaying errors
        int executeUpdateVal;           // Return value from execute indicating effected rows
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        
        int activityId = -1;
        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), "usermanagement");
            s = DBConn.createStatement();
            SQLstatement = ("INSERT INTO user_activities (user_id, login_time) VALUES ( " + userID + ", '"
                    + dateTimeStamp + "' );");

            executeUpdateVal = s.executeUpdate(SQLstatement);

            res = s.executeQuery("SELECT LAST_INSERT_ID()");
            if (res.next()) {
                activityId = res.getInt(1);
            }            

        } catch (ConnectionFailedException ex) {            
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activityId;

    }

}
