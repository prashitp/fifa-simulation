package com.gameplay.service;

import com.gameplay.entity.PlayerEntity;
import com.gameplay.repository.IPlayerStatusRepository;
import com.gameplay.repository.PlayerStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class PlayerStatusServiceTest {

	private IPlayerStatusService playerStatusService = new PlayerStatusService();

	private IPlayerStatusRepository playerStatusRepositoryMock = Mockito.mock(PlayerStatusRepository.class);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter setter = new FieldSetter(playerStatusService,
				PlayerStatusService.class.getDeclaredField("playerStatusRepository"));
		setter.set(playerStatusRepositoryMock);
	}

	@Test
	@Order(1)
	public void fetchAllPlayerValidTest() {
		when(playerStatusRepositoryMock.fetchAllPlayers(Mockito.anyInt()))
				.thenReturn(Arrays.asList(new PlayerEntity()));
		List<PlayerEntity> players = playerStatusService.fetchAllPlayers(1);
		assertTrue(players.size() == 1, "fetchAllPlayer(teamId) method is not working as expected.");
	}

	@Test
	@Order(2)
	public void fetchAllPlayerInvalidTest() {
		when(playerStatusRepositoryMock.fetchAllPlayers(Mockito.anyInt())).thenReturn(Arrays.asList());
		List<PlayerEntity> players = playerStatusService.fetchAllPlayers(0);
		assertTrue(players.size() == 0, "fetchAllPlayer(teamId) method is not working as expected.");
	}

	@Test
	@Order(3)
	public void fetchPlayerValidTest() {
		when(playerStatusRepositoryMock.fetchPlayer(Mockito.any())).thenReturn(new PlayerEntity());
		PlayerEntity player = playerStatusService.fetchPlayer(1);
		assertTrue(player != null, "fetchPlayer(playerId) method is not working as expected.");
	}

	@Test
	@Order(4)
	public void fetchPlayerInvalidTest() {
		when(playerStatusRepositoryMock.fetchPlayer(Mockito.any())).thenReturn(null);
		PlayerEntity player = playerStatusService.fetchPlayer(0);
		assertTrue(player == null, "fetchPlayer(playerId) method is not working as expected.");
	}

	@Test
	@Order(5)
	public void deleteAllPlayersTest() {
		when(playerStatusRepositoryMock.deleteAllPlayers()).thenReturn(Boolean.TRUE);
		assertTrue(playerStatusService.deleteAllPlayers(), "deleteAllPlayers() method is not working as expected.");
	}

	@Test
	@Order(6)
	public void copyFromPlayerToPlayerStatus() {
		when(playerStatusRepositoryMock.copyFromPlayerToPlayerStatus()).thenReturn(Boolean.TRUE);
		assertTrue(playerStatusService.copyFromPlayerToPlayerStatus(),
				"copyFromPlayerToPlayerStatus() method is not working as expected.");
	}
}
