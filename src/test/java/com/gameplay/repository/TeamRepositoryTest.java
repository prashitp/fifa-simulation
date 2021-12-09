package com.gameplay.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jay Patel
 */
public class TeamRepositoryTest {

	private ITeamRepository teamRepository = new TeamRepository();

	@Test
	public void checkValidTeamIdExist() {
		assertTrue(teamRepository.isTeamIdExists(1),
				"isTeamIdExists(teamId) is not working as expected for valid team id.");
	}

	@Test
	public void checkInvalidTeamIdExist() {
		assertFalse(teamRepository.isTeamIdExists(0),
				"isTeamIdExists(teamId) is not working as expected for invalid team id.");
	}

	@Test
	public void fetchAllTeamsTest() {
		assertTrue(teamRepository.fetchAllTeams().size() == 20, "fetchAllTeam() is not working as expected.");
	}
}
