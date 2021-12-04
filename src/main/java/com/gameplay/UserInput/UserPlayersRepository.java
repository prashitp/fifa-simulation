package com.gameplay.UserInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;

import com.LogService;
import com.database_operations.DatabaseConnection;

/**
 * @author Jay Patel
 */
public class UserPlayersRepository implements IUserPlayersRepository {

	private Connection databaseConnection;
	private LogService logService;
	private PreparedStatement statement;

	public UserPlayersRepository() {
		try {
			logService = new LogService();
			databaseConnection = DatabaseConnection.getInstance().getConnection();
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public Boolean selectPlayer(Integer playerId) {
		return null;
	}
}
