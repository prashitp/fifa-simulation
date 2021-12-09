package com.gameplay.service;

import com.gameplay.entity.PlayerEntity;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusService {

	public PlayerEntity fetchPlayer(Integer playerId);

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);

	public Boolean deleteAllPlayers();

	public Boolean copyFromPlayerToPlayerStatus();
	
	public List<PlayerEntity> fetchAllPlayers();
	
	public Boolean savePlayer(PlayerEntity player);
}
