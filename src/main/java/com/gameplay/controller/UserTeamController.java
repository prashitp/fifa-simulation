package com.gameplay.controller;

import com.gameplay.service.IUserTeamService;
import com.gameplay.service.UserTeamService;
import com.models.UserTeamModel;

public class UserTeamController implements IUserTeamController {

	private IUserTeamService userTeamService;

	public UserTeamController() {
		userTeamService = new UserTeamService();
	}

	@Override
	public Boolean setUserTeam(Integer teamId) {
		return userTeamService.setUserTeam(teamId);
	}

	@Override
	public UserTeamModel fetchUserTeamModel() {
		return userTeamService.getUserTeam();
	}

	@Override
	public Boolean deleteUserTeamModel() {
		return userTeamService.deleteUserTeam();
	}

	@Override
	public Boolean customizePlayingXI(Boolean flag) {
		return userTeamService.customizePlayingXI(flag);
	}
}
