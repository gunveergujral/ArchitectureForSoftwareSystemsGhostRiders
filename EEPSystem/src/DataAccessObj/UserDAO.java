/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObj;

import JavaBeans.UserAccount;
import UserInterface.LoginFrame;
import Utilities.DBConnector;
import Utilities.UserSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author songxiong
 */
public class UserDAO {

    public UserAccount getUser(String username) {
        Connection DBConn = null;       // MySQL connection handle
        Statement s = null;             // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        Boolean executeError = false;   // Error flag
        String errString = null;        // String for displaying errors
        int executeUpdateVal;           // Return value from execute indicating effected rows
        String msgString = null;            // String for displaying non-error messages
        ResultSet res = null;               // SQL query result set pointer
        UserAccount user = null;
        try {
            DBConn = DBConnector.getConnection(UserSession.getDatabaseIP(), "usermanagement");
            s = DBConn.createStatement();
            res = s.executeQuery("Select * from user_account where username = '" + username + "'");
                        
            if (res.next()) {
                user = new UserAccount();
                int userId = res.getInt(1);
                ArrayList<String> roles = new ArrayList<String>();
                
                user.setUserID(userId);
                user.setUsername(username);
                user.setPassword(res.getString(3));
                
                res = s.executeQuery("Select user_role_id from user_role_relation where user_id = " + userId);
                while (res.next()) {
                    roles.add(res.getString(1));
                }
                user.setRoles(roles);
            }

            
        } catch (Exception ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
