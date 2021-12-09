package com.gameplay.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

/**
 * @author Jay Patel
 */
public class PlayerTrainingServiceTest {

	private IPlayerTrainingService playerTrainingService = new PlayerTrainingService();
	private IPlayerStatusService playerStatusServiceMock = Mockito.mock(IPlayerStatusService.class);
	private IPlayerStatusService playerStatusService = new PlayerStatusService();

	@Test
	public void performTrainingSessionTest() throws Exception {

		FieldSetter playerStatusServiceSetter = new FieldSetter(playerTrainingService,
				PlayerTrainingService.class.getDeclaredField("playerStatusService"));
		playerStatusServiceSetter.set(playerStatusServiceMock);

		Mockito.when(playerStatusServiceMock.fetchAllPlayers()).thenReturn(playerStatusService.fetchAllPlayers());

		Mockito.when(playerStatusServiceMock.savePlayer(Mockito.any())).thenReturn(Boolean.TRUE, Boolean.TRUE,
				Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
				Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
				Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);

		assertTrue(playerTrainingService.performTrainingSession(),
				"performTrainingSession() method is not working as expected.");
	}

}
