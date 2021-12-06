package com.gameplay;

import com.gameplay.UserInput.IUserInputFunction;
import com.gameplay.UserInput.UserInputFunction;

/**
 * @author Jay Patel
 */
public class Game implements IGame {

	public IUserInputFunction userInputFunction;

	public Game() {
		userInputFunction = new UserInputFunction();
	}

	@Override
	public void startGame() {
		userInputFunction.teamSelection();
	}
}
