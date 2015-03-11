/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunveer Gujral
 */
public class UserSession {
   private static int userID;
   private static int activityID;
   private static String databaseIP;

    /**
     * @return the userID
     */
    public static int getUserID() {
        return userID;
    }

    /**
     * @param aUserID the userID to set
     */
    public static void setUserID(int aUserID) {
        userID = aUserID;
    }

    /**
     * @return the activityID
     */
    public static int getActivityID() {
        return activityID;
    }

    /**
     * @param aActivityID the activityID to set
     */
    public static void setActivityID(int aActivityID) {
        activityID = aActivityID;
    }
    
        /**
     * @return the databaseIP
     */
    public static String getDatabaseIP() {
        return databaseIP;
    }

    /**
     * @param aDatabaseIP the databaseIP to set
     */
    public static void setDatabaseIP(String aDatabaseIP) {
        databaseIP = aDatabaseIP;
    }
   
   static Connection  DBConn = null;
   static int executeUpdateVal;           // Return value from execute indicating effected rows
   static java.sql.Statement s = null;        // SQL statement pointer
   static String SQLstatement = null;        // String for building SQL queries

   
   public static void updateUserActivities() {
       try {
           
           DBConn = DBConnector.getConnection(getDatabaseIP(),"usermanagement");
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           String formattedTimeStamp = formatter.format(new Date());
           s = DBConn.createStatement();

           SQLstatement = ("UPDATE user_activities set logout_time='"+formattedTimeStamp+"' where user_activity_id = '" + getActivityID() + "';");
           executeUpdateVal = s.executeUpdate(SQLstatement);

       } catch (Exception ex) {
           Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
       }
   }




}
