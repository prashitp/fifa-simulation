package com.gameplay.repository;

import com.utils.LogService;
import com.database_operations.DatabaseConnection;
import com.utils.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;

/**
 * @author Jay Patel
 */
public class UserPlayersRepository implements IUserPlayersRepository {

	public static final String ADD_PLAYER_QUERY = "INSERT INTO user_players VALUES (?)";

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
			statement = databaseConnection.prepareStatement(ADD_PLAYER_QUERY);
			statement.setString(1, Converter.convertToPlayerIdString(playerId));
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}
}
