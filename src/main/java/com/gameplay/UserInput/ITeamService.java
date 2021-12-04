package com.gameplay.UserInput;

import java.util.List;

import com.models.ClubModel;

/**
 * @author Jay Patel
 */
public interface ITeamService {

	public List<ClubModel> fetchAllTeams();

	public Boolean isTeamIdExists(Integer teamId);
}
