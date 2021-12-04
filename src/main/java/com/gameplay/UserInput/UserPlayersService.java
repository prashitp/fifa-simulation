package com.gameplay.UserInput;

import java.util.List;

import com.LogService;
import com.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public class UserPlayersService implements IUserPlayersService {

	private IUserPlayersRepository userPlayerRepository;

	private LogService logService;

	public UserPlayersService() {
		userPlayerRepository = new UserPlayersRepository();
		logService = new LogService();
	}

	@Override
	public Boolean selectPlayer(Integer playerId) {
		return userPlayerRepository.selectPlayer(playerId);
	}

	@Override
	public Boolean setUsersPlayingXI(List<PlayerEntity> selectedPlayers) {
		return null;
	}
}
