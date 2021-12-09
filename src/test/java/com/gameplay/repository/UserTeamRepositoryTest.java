package com.gameplay.repository;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class UserTeamRepositoryTest {

	private IUserTeamRepository userTeamRepository = new UserTeamRepository();

	private Connection connectionMock = Mockito.mock(Connection.class);

	@Test
	@Order(1)
	public void setUserTeamTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(userTeamRepository.setUserTeam(1), "setUserTeam(teamId) is not working as expected.");
	}

//	@Test
//	@Order(2)
//	public void fetchUserTeamTest() {
//		assertNotNull(userTeamRepository.fetchUserTeam(), "fetchUserTeam() method is not working as expected.");
//	}

	@Test
	@Order(3)
	public void customizePlayingXITest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(userTeamRepository.customizePlayingXI(Boolean.TRUE),
				"customizePlayingXI(flag) is not working as expected.");
	}

	@Test
	@Order(4)
	public void deleteUserTeamTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(userTeamRepository.deleteUserTeam(), "deleteUserTeam() is not working as expected.");
	}

	@Test
	@Order(5)
	public void deleteUserTeamInvalidTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(userTeamRepository.deleteUserTeam(), "deleteUserTeam() is not working as expected.");
	}

	@Test
	@Order(6)
	public void fetchUserTeamInvalidTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertNull(userTeamRepository.fetchUserTeam(), "fetchUserTeam() method is not working as expected.");
	}

	@Test
	@Order(7)
	public void customizePlayingXIInvalidTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(userTeamRepository.customizePlayingXI(Boolean.TRUE),
				"customizePlayingXI(flag) is not working as expected.");
	}

	@Test
	@Order(8)
	public void setUserTeamInvalidTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(userTeamRepository.setUserTeam(0), "setUserTeam(teamId) is not working as expected.");
	}

	@Test
	@Order(9)
	public void setSeasonPlayedTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(userTeamRepository.setSeasonPlayed(1), "setUserTeam(teamId) is not working as expected.");
	}

	@Test
	@Order(10)
	public void setSeasonPlayedInvalidTest() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamRepository,
				UserTeamRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(userTeamRepository.setSeasonPlayed(0), "setUserTeam(teamId) is not working as expected.");
	}
}
