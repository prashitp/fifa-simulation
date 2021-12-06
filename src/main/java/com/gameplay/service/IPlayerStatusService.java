package com.gameplay.service;

import java.util.List;

import com.gameplay.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusService {

	public PlayerEntity fetchPlayer(Integer playerId);

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);

	public Boolean deleteAllPlayers();

	public Boolean copyFromPlayerToPlayerStatus();
}
