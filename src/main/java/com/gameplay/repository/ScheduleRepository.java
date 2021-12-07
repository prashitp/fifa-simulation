package com.gameplay.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;

import com.LogService;
import com.database_operations.DatabaseConnection;
import com.models.MatchModel;

/**
 * @author Jay Patel
 */
public class ScheduleRepository implements IScheduleRepository {

	private Connection databaseConnection;
	private LogService logService;
	private PreparedStatement statement;

	private static final String DELETE_SCHEDULE_QUERY = "DELETE FROM schedule";
	private static final String ADD_SCHEDULE_QUERY = "INSERT INTO schedule VALUES (?, ?)";

	public ScheduleRepository() {
		try {
			logService = new LogService();
			databaseConnection = DatabaseConnection.getInstance().getConnection();
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public Boolean deleteSchedule() {
		return Boolean.FALSE;
	}

	@Override
	public Boolean saveSchedule(List<MatchModel> matches) {

		return Boolean.FALSE;
	}

}
