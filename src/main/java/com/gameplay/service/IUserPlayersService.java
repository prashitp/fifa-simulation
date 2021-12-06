package com.gameplay.service;

import java.util.List;

import com.gameplay.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IUserPlayersService {

	public Boolean selectPlayer(Integer playerId);

	public Boolean setUsersPlayingXI(List<PlayerEntity> selectedPlayers);

}
