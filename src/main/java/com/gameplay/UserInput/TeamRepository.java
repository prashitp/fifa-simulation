package com.gameplay.UserInput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.database_operations.DatabaseImport;
import com.models.ClubModel;

/**
 * @author Jay Patel
 */
public class TeamRepository implements ITeamRepository {

	@SuppressWarnings("static-access")
	@Override
	public List<ClubModel> fetchAllTeams() {
		return Arrays.asList(DatabaseImport.getInstance().getClubs());
	}

	@SuppressWarnings("static-access")
	@Override
	public Boolean isTeamIdExists(Integer teamId) {
		return Arrays.asList(DatabaseImport.getInstance().getClubs()).stream().map(club -> club.getClubId())
				.collect(Collectors.toList()).contains(teamId);
	}
}
