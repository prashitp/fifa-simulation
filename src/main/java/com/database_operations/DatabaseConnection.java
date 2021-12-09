package com.database_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import com.utils.Constants;
import com.utils.LogService;

/**
 * @author Mayank Sareen
 */

public class DatabaseConnection {
	LogService logService = new LogService();
	private Connection connection;

	private DatabaseConnection() {
		try {
			connection = DriverManager.getConnection(Constants.CONNECTION_DEV_URL, Constants.CONNECTION_USERNAME,
					Constants.CONNECTION_PASSWORD);
		} catch (SQLException ex) {
			logService.log(Level.SEVERE,
					"ERROR! Exception occurred while connecting to Database :: " + ex.getMessage());
		}
	}

	private static DatabaseConnection instance;

	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	protected void finalize() {
		try {
			connection.close();
		} catch (SQLException ex) {
			logService.log(Level.SEVERE, "ERROR! Exception occurred while closing Database :: " + ex.getMessage());
		}
	}
}
