/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import UserMgr.LoginFrame;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author songxiong
 */
public class DBConnector {

    private final static String PROPERTIESPATH = "/com/neusc/qpgen/util/connection.properties";
    private final static String MYSQLURL = "jdbc:mysql://localhost:";

    /**
     * This method is used to get a new connector
     *
     * @return Connector
     * @throws ConnectionFailedException
     */
    public static Connection getConnection(String databaseIP, String dbname) throws ConnectionFailedException {

        Connection conn = null;

        try {
            //load JDBC driver class for MySQL
            Class.forName("com.mysql.jdbc.Driver");
            String sourceURL = "jdbc:mysql://" + databaseIP + "3306/" + dbname;
            conn = DriverManager.getConnection(sourceURL, "remote", "remote_pass");
        } catch (Exception ex) {
            throw new ConnectionFailedException("Problem connecting to database.");
        }

        return conn;
    }

}
