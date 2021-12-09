package com.gameplay.controller;

import com.gameplay.entity.PlayerEntity;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusController {

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);
	
	public PlayerEntity fetchPlayer(Integer playerId);

}
