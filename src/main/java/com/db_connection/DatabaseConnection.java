package com.db_connection;
import java.util.logging.*;
import com.Constants;
import com.LogService;
import java.sql.*;
/**
 * @author Mayank Sareen
 */
public class DatabaseConnection {
    public static void main(String args[]) {
        Connection connection = setConnectionWithDatabase();
    }

    public static Connection setConnectionWithDatabase() {
        LogService logService = new LogService();
        logService.initializeLogger();
        Connection connection = null;
        try {
            Class.forName(Constants.JDBC_CLASS_INITIALIZATION);
            connection = DriverManager.getConnection(Constants.CONNECTION_DEV_URL, Constants.CONNECTION_USERNAME,
                    Constants.CONNECTION_PASSWORD);
            if (connection != null && !connection.isClosed()) {
                logService.log(Level.INFO, "Connection to Database Successful!");
            }
        } catch(Exception ex) {
            logService.log(Level.SEVERE, "ERROR! Exception occurred while connecting to Database" + ex.getStackTrace());
        }
        return connection;
    }
}
