package com.gameplay.UserInput;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.LogService;
import com.database_operations.DatabaseConnection;
import com.models.UserTeamModel;

/**
 * @author Jay Patel
 */
public class UserTeamRepository implements IUserTeamRepository {

	private Connection databaseConnection;
	private LogService logService;
	private PreparedStatement statement;

	public UserTeamRepository() {
		logService = new LogService();
		databaseConnection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public Boolean setUserTeam(Integer teamId) {
		return null;
	}

	@Override
	public Boolean customizePlayingXI(Boolean flag) {
		return null;
	}

	@Override
	public Boolean setSeasonPlayed(Integer seasonPlayed) {
		return null;
	}

	@Override
	public UserTeamModel fetchUserTeam() {
		return null;
	}

	@Override
	public Boolean deleteUserTeam() {
		return null;
	}
}
