package com.gameplay.UserInput;

import java.util.List;

import com.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusService {

	public PlayerEntity fetchPlayer(Integer playerId);

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);

	public Boolean deleteAllPlayers();

	public Boolean copyFromPlayerToPlayerStatus();
}
