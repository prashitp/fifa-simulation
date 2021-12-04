package com.gameplay.UserInput;

/**
 * @author Jay Patel
 */
public class UserInputController implements IUserInputController {

	private IUserInputService userInputService;

	public UserInputController() {
		userInputService = new UserInputService();
	}

	@Override
	public Integer selectTeamId() {
		return null;
	}

	@Override
	public void selectPlayingXI(Integer teamId) {
	}

	@Override
	public Boolean customizePlayingXI() {
		return null;
	}

	@Override
	public Boolean startNewGame() {
		return userInputService.startNewGame();
	}
}
