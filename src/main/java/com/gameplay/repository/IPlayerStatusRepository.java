package com.gameplay.repository;

import com.gameplay.entity.PlayerEntity;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusRepository {

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);

	public PlayerEntity fetchPlayer(Integer playerId);

	public Boolean deleteAllPlayers();
	
	public Boolean copyFromPlayerToPlayerStatus();
	
	public List<PlayerEntity> fetchAllPlayers();
	
	public Boolean savePlayer(PlayerEntity player);
}
