package com.gameplay.UserInput;

import java.util.List;

import com.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface IUserPlayersService {

	public Boolean selectPlayer(Integer playerId);

	public Boolean setUsersPlayingXI(List<PlayerEntity> selectedPlayers);

}
