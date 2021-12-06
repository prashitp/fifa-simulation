package com.gameplay.service;

import java.util.List;
import java.util.logging.Level;

import com.LogService;
import com.gameplay.entity.PlayerEntity;
import com.gameplay.repository.IUserPlayersRepository;
import com.gameplay.repository.UserPlayersRepository;
import com.utils.Converter;

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
		try {
			selectedPlayers.forEach(player -> userPlayerRepository
					.selectPlayer(Converter.convertToPlayerIdInteger(player.getPlayerId())));
			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}
}
