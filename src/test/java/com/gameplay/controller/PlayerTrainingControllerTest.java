package com.gameplay.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.service.IPlayerTrainingService;

/**
 * @author Jay Patel
 */
public class PlayerTrainingControllerTest {

	private IPlayerTrainingController playerTrainingController = new PlayerTrainingController();
	private IPlayerTrainingService playerTrainingServiceMock = Mockito.mock(IPlayerTrainingService.class);

	@Test
	public void performTrainingSessionTest() throws Exception {
		FieldSetter playerTrainingServiceSetter = new FieldSetter(playerTrainingController,
				PlayerTrainingController.class.getDeclaredField("playerTrainingService"));
		playerTrainingServiceSetter.set(playerTrainingServiceMock);

		Mockito.when(playerTrainingServiceMock.performTrainingSession()).thenReturn(Boolean.TRUE);

		assertTrue(playerTrainingController.performTrainingSession(),
				"performTrainingSession() method is not working as expected.");
	}

}
