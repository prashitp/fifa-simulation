package com.gameplay.service;

import com.gameplay.entity.PlayerEntity;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface IUserPlayersService {

	public Boolean selectPlayer(Integer playerId);

	public Boolean setUsersPlayingXI(List<PlayerEntity> selectedPlayers);

}
