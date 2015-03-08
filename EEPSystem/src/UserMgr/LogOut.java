/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunveer Gujral
 */
public class LogOut {
   String userID;
   String activityID;
   Connection DBConn = null;
   java.sql.Statement s = null;        // SQL statement pointer
   String SQLstatement = null;        // String for building SQL queries
   public LogOut(String userID, String activityID){
    this.userID = userID;
    this.activityID = activityID;
   }
   public void updateUserActivities(String SQLServerIP) throws ClassNotFoundException{
       try {
           System.out.println("Update the user activity here");
           Class.forName( "com.mysql.jdbc.Driver" );
           String sourceURL = "jdbc:mysql://" + SQLServerIP + ":3306/usermanagement";
           DBConn = DriverManager.getConnection(sourceURL,"remote","remote_pass");
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String formattedTimeStamp = formatter.format(new Date());
           s = DBConn.createStatement();
           SQLstatement = ("UPDATE user_activities set logout_time="+formattedTimeStamp+"where user_activity_id = '" + activityID + "';");
       } catch (SQLException ex) {
           Logger.getLogger(LogOut.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
