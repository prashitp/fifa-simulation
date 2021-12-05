package com.gameplay.UserInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.Constants.DatabaseConstants;
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
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.INSERT_USER_TEAM_QUERY);
			statement.setString(1, "C" + ((teamId >= 10) ? teamId.toString() : "0" + teamId));
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean customizePlayingXI(Boolean flag) {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.UPDATE_CUSTOMIZED_PLAYING_XI_QUERY);
			statement.setInt(1, (flag ? 1 : 0));
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean setSeasonPlayed(Integer seasonPlayed) {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.UPDATE_SEASON_PLAYED_QUERY);
			statement.setInt(1, seasonPlayed);
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}

	@Override
	public UserTeamModel fetchUserTeam() {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.FETCH_USER_TEAM_QUERY);
			ResultSet rs = statement.executeQuery();
			List<UserTeamModel> userTeamList = new ArrayList<>();
			while (rs.next()) {
				UserTeamModel userTeam = new UserTeamModel(rs.getInt("id"),
						Integer.parseInt(rs.getString("team_id").substring(1)), rs.getBoolean("customized_player"),
						rs.getInt("season_played"));
				userTeamList.add(userTeam);
			}
			if (userTeamList.size() > 0) {
				return userTeamList.get(0);
			}
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean deleteUserTeam() {
		try {
			statement = databaseConnection.prepareStatement(DatabaseConstants.DELETE_ALL_USER_TEAM_QUERY);
			statement.executeUpdate();
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}
}
