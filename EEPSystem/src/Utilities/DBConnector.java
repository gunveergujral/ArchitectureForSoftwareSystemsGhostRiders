/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import UserInterface.LoginFrame;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author songxiong
 */
public class DBConnector {

    /**
     * This method is used to get a new connector
     *
     * @return Connector
     * @throws ConnectionFailedException
     */
    public static Connection getConnection(String databaseIP, String dbname) throws Exception {

        Connection conn = null;

        Class.forName("com.mysql.jdbc.Driver");
        String sourceURL = "jdbc:mysql://" + databaseIP + ":3306/" + dbname;
        conn = DriverManager.getConnection(sourceURL, "remote", "remote_pass");

        return conn;
    }

}
