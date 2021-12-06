package com.gameplay.UserInput;

import java.util.List;

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
