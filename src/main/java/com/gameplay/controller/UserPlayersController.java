package com.gameplay.controller;

import java.util.List;

import com.gameplay.entity.PlayerEntity;
import com.gameplay.service.IUserPlayersService;
import com.gameplay.service.UserPlayersService;

public class UserPlayersController implements IUserPlayersController {

	private IUserPlayersService userPlayersService;

	public UserPlayersController() {
		userPlayersService = new UserPlayersService();
	}

	@Override
	public Boolean setPlayingXI(List<PlayerEntity> players) {
		return userPlayersService.setUsersPlayingXI(players);
	}

}
