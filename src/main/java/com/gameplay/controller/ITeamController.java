package com.gameplay.controller;

import java.util.List;

import com.models.ClubModel;

public interface ITeamController {

	public List<ClubModel> fetchAllTeams();
	
	public Boolean isTeamIdExists(Integer teamId);

}
