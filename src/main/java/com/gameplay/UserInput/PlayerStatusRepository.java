package com.gameplay.UserInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;

import com.LogService;
import com.database_operations.DatabaseConnection;
import com.entity.PlayerEntity;

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
		return null;
	}

	@Override
	public PlayerEntity fetchPlayer(Integer playerId) {
		return null;
	}

	@Override
	public Boolean deleteAllPlayers() {
		return Boolean.FALSE;
	}

	@Override
	public Boolean copyFromPlayerToPlayerStatus() {
		return Boolean.FALSE;
	}
}
