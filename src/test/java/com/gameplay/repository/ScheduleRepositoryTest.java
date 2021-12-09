package com.gameplay.repository;

import com.models.MatchModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jay Patel
 */
public class ScheduleRepositoryTest {

	private IScheduleRepository scheduleRepository = new ScheduleRepository();

	@Test
	public void deleteScheduleTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter connection = new FieldSetter(scheduleRepository,
				ScheduleRepository.class.getDeclaredField("databaseConnection"));
		connection.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);
		assertTrue(scheduleRepository.deleteSchedule(), "deleteSchedule() method is not working as expected.");
	}
	
	@Test
	public void deleteScheduleInvalidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter connection = new FieldSetter(scheduleRepository,
				ScheduleRepository.class.getDeclaredField("databaseConnection"));
		connection.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);
		assertFalse(scheduleRepository.deleteSchedule(), "deleteSchedule() method is not working as expected.");
	}

	@Test
	public void saveScheduleTest() throws Exception {
		MatchModel match = new MatchModel("C01", "C02");

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter connection = new FieldSetter(scheduleRepository,
				ScheduleRepository.class.getDeclaredField("databaseConnection"));
		connection.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);
		assertTrue(scheduleRepository.saveSchedule(Arrays.asList(match)),
				"deleteSchedule() method is not working as expected.");
	}
	
	@Test
	public void saveScheduleInvalidTest() throws Exception {
		MatchModel match = new MatchModel("C01", "C02");

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter connection = new FieldSetter(scheduleRepository,
				ScheduleRepository.class.getDeclaredField("databaseConnection"));
		connection.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);
		assertFalse(scheduleRepository.saveSchedule(Arrays.asList(match)),
				"deleteSchedule() method is not working as expected.");
	}
}
