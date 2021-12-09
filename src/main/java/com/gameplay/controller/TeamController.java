package com.gameplay.controller;

import com.gameplay.service.ITeamService;
import com.gameplay.service.TeamService;
import com.models.ClubModel;

import java.util.List;

/**
 * @author Jay Patel
 */
public class TeamController implements ITeamController {

	private ITeamService teamService;

	public TeamController() {
		teamService = new TeamService();
	}

	@Override
	public List<ClubModel> fetchAllTeams() {
		return teamService.fetchAllTeams();
	}

	@Override
	public Boolean isTeamIdExists(Integer teamId) {
		return teamService.isTeamIdExists(teamId);
	}

}
