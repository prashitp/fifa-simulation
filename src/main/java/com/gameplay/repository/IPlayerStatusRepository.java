package com.gameplay.repository;

import java.util.List;

import com.gameplay.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusRepository {

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);

	public PlayerEntity fetchPlayer(Integer playerId);

	public Boolean deleteAllPlayers();
	
	public Boolean copyFromPlayerToPlayerStatus();
}
