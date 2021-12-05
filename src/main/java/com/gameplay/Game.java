package com.gameplay;

import com.gameplay.UserInput.IUserInputController;
import com.gameplay.UserInput.UserInputController;
import com.io.IOutputStream;
import com.io.StandardOutputStream;

/**
 * @author Jay Patel
 */
public class Game implements IGame {

	private IUserInputController userInputController = new UserInputController();
	private IOutputStream outputStream = StandardOutputStream.getInstance();
	
	@Override
	public void startGame() {
		startOrResumeGame();
	}
	
	private void startOrResumeGame() {
		if(userInputController.startNewGame()) {
			outputStream.println("Starting a new Game.");
			Integer teamId = userInputController.selectTeamId();
			if(userInputController.customizePlayingXI()) {
				userInputController.selectPlayingXI(teamId);
			}
		} else {
			outputStream.println("Resuming previous Game.");
		}
	}
}
