package com.gameplay.controller;

import com.gameplay.service.IPlayerTrainingService;
import com.gameplay.service.PlayerTrainingService;

/**
 * @author Jay Patel
 */
public class PlayerTrainingController implements IPlayerTrainingController {

	private IPlayerTrainingService playerTrainingService;

	public PlayerTrainingController() {
		playerTrainingService = new PlayerTrainingService();
	}

	@Override
	public Boolean performTrainingSession() {
		return playerTrainingService.performTrainingSession();
	}

}
