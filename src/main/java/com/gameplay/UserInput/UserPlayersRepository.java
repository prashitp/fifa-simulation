package com.gameplay.UserInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;

import com.Constants.DatabaseConstants;
import com.LogService;
import com.database_operations.DatabaseConnection;
import com.utils.Converter;

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
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.ADD_PLAYER_QUERY);
			statement.setString(1, Converter.convertToPlayerIdString(playerId));
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}
}
