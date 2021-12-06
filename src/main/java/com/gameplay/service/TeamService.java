package com.gameplay.service;

import java.util.List;

import com.gameplay.repository.ITeamRepository;
import com.gameplay.repository.TeamRepository;
import com.models.ClubModel;

/**
 * @author Jay Patel
 */
public class TeamService implements ITeamService {

	private ITeamRepository teamRepository;

	public TeamService() {
		teamRepository = new TeamRepository();
	}

	@Override
	public List<ClubModel> fetchAllTeams() {
		return teamRepository.fetchAllTeams();
	}

	@Override
	public Boolean isTeamIdExists(Integer teamId) {
		return teamRepository.isTeamIdExists(teamId);
	}
}
