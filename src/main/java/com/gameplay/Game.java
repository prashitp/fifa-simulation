package com.gameplay;

import java.util.List;

import com.gameplay.UserInput.IUserInputFunction;
import com.gameplay.UserInput.UserInputFunction;
import com.gameplay.controller.IScheduleController;
import com.gameplay.controller.ScheduleController;
import com.models.MatchModel;

/**
 * @author Jay Patel
 */
public class Game implements IGame {

	public IUserInputFunction userInputFunction;

	public IScheduleController scheduleController;

	public Game() {
		userInputFunction = new UserInputFunction();
		scheduleController = new ScheduleController();
	}

	@Override
	public void startGame() {
//		userInputFunction.teamSelection();
		List<MatchModel> matches = scheduleController.createMatchSchedule();
	}
}
