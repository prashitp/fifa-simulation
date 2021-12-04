package com.gameplay.UserInput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

/**
 * @author Jay Patel
 */
public class UserInputControllerTest {

	private IUserInputController userInputController = new UserInputController();
	private IUserInputService userInputService = Mockito.mock(UserInputService.class);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter teamService = new FieldSetter(userInputController,
				UserInputController.class.getDeclaredField("userInputService"));
		teamService.set(userInputService);
	}

	@Test
	public void selectTeamIdTest() {
		Mockito.when(userInputService.selectTeamId()).thenReturn(1);
		userInputController.selectTeamId();
	}

	@Test
	public void selectPlayingXITest() {
		Mockito.when(userInputService.selectPlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		userInputController.selectPlayingXI(1);
	}

	@Test
	public void customizedPlayingXITest() {
		Mockito.when(userInputService.customizePlayingXI()).thenReturn(Boolean.TRUE);
		userInputController.customizePlayingXI();
	}

	@Test
	public void startNewGameTest() {
		userInputController.startNewGame();
	}
}
