package com.gameplay.repository;

import com.models.ClubModel;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface ITeamRepository {

	public List<ClubModel> fetchAllTeams();

	public Boolean isTeamIdExists(Integer teamId);
}
