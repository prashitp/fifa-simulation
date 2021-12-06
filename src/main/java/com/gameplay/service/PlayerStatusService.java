package com.gameplay.service;

import java.util.List;

import com.gameplay.entity.PlayerEntity;
import com.gameplay.repository.IPlayerStatusRepository;
import com.gameplay.repository.PlayerStatusRepository;

/**
 * @author Jay Patel
 */
public class PlayerStatusService implements IPlayerStatusService {

	private IPlayerStatusRepository playerStatusRepository;

	public PlayerStatusService() {
		playerStatusRepository = new PlayerStatusRepository();
	}

	@Override
	public PlayerEntity fetchPlayer(Integer playerId) {
		return playerStatusRepository.fetchPlayer(playerId);
	}

	@Override
	public List<PlayerEntity> fetchAllPlayers(Integer teamId) {
		return playerStatusRepository.fetchAllPlayers(teamId);
	}

	@Override
	public Boolean deleteAllPlayers() {
		return playerStatusRepository.deleteAllPlayers();
	}

	@Override
	public Boolean copyFromPlayerToPlayerStatus() {
		return playerStatusRepository.copyFromPlayerToPlayerStatus();
	}
}
