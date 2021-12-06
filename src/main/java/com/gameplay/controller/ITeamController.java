package com.gameplay.controller;

import java.util.List;

import com.models.ClubModel;

/**
 * @author Jay Patel
 */
public interface ITeamController {

	public List<ClubModel> fetchAllTeams();
	
	public Boolean isTeamIdExists(Integer teamId);

}
