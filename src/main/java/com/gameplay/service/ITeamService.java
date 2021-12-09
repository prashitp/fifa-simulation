package com.gameplay.service;

import com.models.ClubModel;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface ITeamService {

	public List<ClubModel> fetchAllTeams();

	public Boolean isTeamIdExists(Integer teamId);
}
