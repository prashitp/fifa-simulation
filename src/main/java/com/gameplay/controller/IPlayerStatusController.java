package com.gameplay.controller;

import java.util.List;

import com.gameplay.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IPlayerStatusController {

	public List<PlayerEntity> fetchAllPlayers(Integer teamId);
	
	public PlayerEntity fetchPlayer(Integer playerId);

}
