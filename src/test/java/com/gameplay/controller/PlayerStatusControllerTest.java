package com.gameplay.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.entity.PlayerEntity;
import com.gameplay.service.IPlayerStatusService;
import com.gameplay.service.PlayerStatusService;

/**
 * @author Jay Patel
 */
public class PlayerStatusControllerTest {

	private IPlayerStatusService playerStatusServiceMock = Mockito.mock(PlayerStatusService.class);
	private IPlayerStatusController playerStatusController = new PlayerStatusController();

	private PlayerEntity player = new PlayerEntity();

	@BeforeEach
	public void init() throws Exception {
		FieldSetter playerStatusService = new FieldSetter(playerStatusController,
				PlayerStatusController.class.getDeclaredField("playerStatusService"));
		playerStatusService.set(playerStatusServiceMock);
	}

	@Test
	public void fetchAllPlayersTest() {
		Mockito.when(playerStatusServiceMock.fetchAllPlayers(Mockito.any())).thenReturn(Arrays.asList(player));
		assertTrue (playerStatusController.fetchAllPlayers(1).size() == 1, "fetchAllPlayers() method is not working as expected.");
	}
	
	@Test
	public void fetchPlayerTest() {
		Mockito.when(playerStatusServiceMock.fetchPlayer(Mockito.any())).thenReturn(player);
		assertTrue (playerStatusController.fetchPlayer(1) == player, "fetchPlayer() method is not working as expected.");
	}
}
