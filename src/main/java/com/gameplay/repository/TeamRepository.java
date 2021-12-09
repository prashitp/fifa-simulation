package com.gameplay.repository;

import com.databaseOperations.DatabaseImport;
import com.models.ClubModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jay Patel
 */
public class TeamRepository implements ITeamRepository {

	@Override
	public List<ClubModel> fetchAllTeams() {
		return Arrays.asList(DatabaseImport.getInstance().getClubs());
	}

	@Override
	public Boolean isTeamIdExists(Integer teamId) {
		return Arrays.asList(DatabaseImport.getInstance().getClubs()).stream().map(club -> club.getClubId())
				.collect(Collectors.toList()).contains(teamId);
	}
}
