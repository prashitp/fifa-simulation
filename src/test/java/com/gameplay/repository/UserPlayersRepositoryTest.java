package com.gameplay.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jay Patel
 */
public class UserPlayersRepositoryTest {

	private IUserPlayersRepository userPlayerRepository = new UserPlayersRepository();

	private Connection connectionMock = Mockito.mock(Connection.class);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter setter = new FieldSetter(userPlayerRepository,
				UserPlayersRepository.class.getDeclaredField("databaseConnection"));
		setter.set(connectionMock);
	}

	@Test
	public void selectPlayerValidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenReturn(1);

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertTrue(userPlayerRepository.selectPlayer(1), "selectPlayer() method is not working properly as expected!");
	}

	@Test
	public void selectPlayerInvalidTest() throws Exception {
		PreparedStatement statement = Mockito.mock(PreparedStatement.class);
		Mockito.when(statement.executeUpdate()).thenThrow(new RuntimeException());

		Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statement);

		assertFalse(userPlayerRepository.selectPlayer(0), "selectPlayer() method is not working properly as expected!");
	}
}
