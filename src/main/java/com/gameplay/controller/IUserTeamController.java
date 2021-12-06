package com.gameplay.controller;

import com.models.UserTeamModel;

public interface IUserTeamController {

	public Boolean setUserTeam(Integer teamId);
	
	public UserTeamModel fetchUserTeamModel();
	
	public Boolean deleteUserTeamModel();
	
	public Boolean customizePlayingXI(Boolean flag);
}
