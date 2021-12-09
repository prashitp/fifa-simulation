package com.gameplay.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class PlayerStatusRepositoryTest {

	private IPlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();
	private IPlayerStatusRepository playerRepository = new PlayerStatusRepository();
	
	@Test
	@Order(1)
	public void fetchAllPlayerValidTest() throws Exception {
		List<PlayerEntity> players = playerStatusRepository.fetchAllPlayers(1);
		assertTrue(players.size() == 33, "fetchAllPlayer(teamId) method is not working as expected!");
	}

	@Test
	@Order(2)
	public void fetchAllPlayerInvalidTest() {
		List<PlayerEntity> players = playerStatusRepository.fetchAllPlayers(0);
		assertTrue(players.size() == 0, "fetchAllPlayer(teamId) method is not working as expected!");
	}

	@Test
	@Order(3)
	public void fetchPlayerValidTest() {
		PlayerEntity player = playerStatusRepository.fetchPlayer(1);
		assertTrue(player != null, "fetchPlayer(playerId) method is not working as expected!");
	}

	@Test
	@Order(4)
	public void fetchPlayerInvalidTest() {
		PlayerEntity player = playerStatusRepository.fetchPlayer(0);
		assertTrue(player == null, "fetchPlayer(playerId) method is not working as expected!");
	}

	@Test
	@Order(5)
	public void deleteAllPlayerValidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(playerStatusRepository.deleteAllPlayers(), "deleteAllPlayers() method is not working as expected.");
	}

	@Test
	@Order(6)
	public void deleteAllPlayerInvalidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(playerStatusRepository.deleteAllPlayers(), "deleteAllPlayers() method is not working as expected.");
	}

	@Test
	@Order(7)
	public void copyFromPlayerToPlayerStatusValidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(playerStatusRepository.copyFromPlayerToPlayerStatus(),
				"copyFromPlayerToPlayerStatus() method is not working as expected.");
	}

	@Test
	@Order(8)
	public void copyFromPlayerToPlayerStatusInvalidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(playerStatusRepository.copyFromPlayerToPlayerStatus(),
				"copyFromPlayerToPlayerStatus() method is not working as expected.");
	}

	@Test
	@Order(9)
	public void fetchPlayerExceptionTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);

		assertNull(playerStatusRepository.fetchAllPlayers(1),
				"fetchAllPlayer() method is not working as expected when exception is thrown.");
	}

	@Test
	@Order(10)
	public void fetchAllPlayersTest() {
		assertTrue(playerStatusRepository.fetchAllPlayers().size() > 0,
				"fetchAllPlayer() method is not working as expected.");
	}

	@Test
	@Order(11)
	public void savePlayerTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);
		
		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(playerStatusRepository.savePlayer(playerRepository.fetchAllPlayers().get(0)),
				"savePlayer() method is not working as expected.");
	}
	
	@Test
	@Order(12)
	public void savePlayerInvalidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);
		
		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(playerStatusRepository.savePlayer(playerRepository.fetchAllPlayers().get(0)),
				"savePlayer() method is not working as expected.");
	}
	
	@Test
	@Order(13)
	public void fetchAllPlayersInvalidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Connection connectionMock = Mockito.mock(Connection.class);
		FieldSetter setter = new FieldSetter(playerStatusRepository,
				PlayerStatusRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);
		
		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(playerStatusRepository.fetchAllPlayers() != null,
				"fetchAllPlayer() method is not working as expected.");
	}
}
