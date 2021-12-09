package com.gameplay.service;

import com.gameplay.repository.IUserTeamRepository;
import com.gameplay.repository.UserTeamRepository;
import com.models.UserTeamModel;

/**
 * @author Jay Patel
 */
public class UserTeamService implements IUserTeamService {

	private IUserTeamRepository userTeamRepository = new UserTeamRepository();

	@Override
	public Boolean setUserTeam(Integer teamId) {
		return userTeamRepository.setUserTeam(teamId);
	}

	@Override
	public Boolean customizePlayingXI(Boolean flag) {
		return userTeamRepository.customizePlayingXI(flag);
	}

	@Override
	public UserTeamModel getUserTeam() {
		return userTeamRepository.fetchUserTeam();
	}

	@Override
	public Boolean setSeasonPlayed(Integer seasonPlayed) {
		return userTeamRepository.setSeasonPlayed(seasonPlayed);
	}

	@Override
	public Boolean deleteUserTeam() {
		return userTeamRepository.deleteUserTeam();
	}
}
