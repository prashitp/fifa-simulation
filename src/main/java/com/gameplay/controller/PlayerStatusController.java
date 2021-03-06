package com.gameplay.controller;

import com.gameplay.entity.PlayerEntity;
import com.gameplay.service.IPlayerStatusService;
import com.gameplay.service.PlayerStatusService;

import java.util.List;

/**
 * @author Jay Patel
 */
public class PlayerStatusController implements IPlayerStatusController {

	private IPlayerStatusService playerStatusService;

	public PlayerStatusController() {
		playerStatusService = new PlayerStatusService();
	}

	@Override
	public List<PlayerEntity> fetchAllPlayers(Integer teamId) {
		return playerStatusService.fetchAllPlayers(teamId);
	}

	@Override
	public PlayerEntity fetchPlayer(Integer playerId) {
		return playerStatusService.fetchPlayer(playerId);
	}
}
