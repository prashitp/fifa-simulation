package com.gameplay.UserInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;

import com.Constants.DatabaseConstants;
import com.LogService;
import com.database_operations.DatabaseConnection;
import com.entity.PlayerEntity;
import com.exceptions.PlayerNotFoundException;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
public class PlayerStatusRepository implements IPlayerStatusRepository {

	private Connection databaseConnection;
	private LogService logService;
	private PreparedStatement statement;

	public PlayerStatusRepository() {
		try {
			logService = new LogService();
			databaseConnection = DatabaseConnection.getInstance().getConnection();
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public List<PlayerEntity> fetchAllPlayers(Integer teamId) {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.FETCH_ALL_PLAYER_IN_TEAM_QUERY);
			statement.setString(1, Converter.convertToTeamIdString(teamId));
			ResultSet resultSet = statement.executeQuery();
			List<PlayerEntity> playerList = Converter.resultSetToEntityList(resultSet, PlayerEntity.class);
			return playerList;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public PlayerEntity fetchPlayer(Integer playerId) {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.FETCH_PLAYER_BY_ID_QUERY);
			statement.setString(1, Converter.convertToPlayerIdString(playerId));
			ResultSet resultSet = statement.executeQuery();
			List<PlayerEntity> players = Converter.resultSetToEntityList(resultSet, PlayerEntity.class);
			if (players.isEmpty()) {
				throw new PlayerNotFoundException();
			}
			return players.get(0);
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean deleteAllPlayers() {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.DELETE_ALL_PLAYERS_QUERY);
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean copyFromPlayerToPlayerStatus() {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.COPY_PLAYERS_DATA_QUERY);
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}
}
