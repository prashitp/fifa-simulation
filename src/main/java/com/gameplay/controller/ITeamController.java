package com.gameplay.controller;

import com.models.ClubModel;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface ITeamController {

	public List<ClubModel> fetchAllTeams();
	
	public Boolean isTeamIdExists(Integer teamId);

}
