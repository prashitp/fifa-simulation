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
		Integer teamSelected = null;
		while (teamSelected == null) {
			teamSelected = userInputService.selectTeamId();
		}
		return teamSelected;
	}

	@Override
	public void selectPlayingXI(Integer teamId) {
		Boolean playingXiSelected = Boolean.FALSE;
		while (Boolean.FALSE.equals(playingXiSelected)) {
			playingXiSelected = userInputService.selectPlayingXI(teamId);
		}
	}

	@Override
	public Boolean customizePlayingXI() {
		Boolean customizePlayingXI = null;
		while (customizePlayingXI == null) {
			customizePlayingXI = userInputService.customizePlayingXI();
		}
		return customizePlayingXI;
	}

	@Override
	public Boolean startNewGame() {
		return userInputService.startNewGame();
	}
}
