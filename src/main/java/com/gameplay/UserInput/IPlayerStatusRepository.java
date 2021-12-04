package com.gameplay.UserInput;

import java.util.List;

import com.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusRepository {

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);

	public PlayerEntity fetchPlayer(Integer playerId);

	public Boolean deleteAllPlayers();
	
	public Boolean copyFromPlayerToPlayerStatus();
}
