package com.gameplay.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jay Patel
 */
public class TeamServiceTest {

	private ITeamService teamService = new TeamService();

	@Test
	public void checkValidTeamIdExist() {
		assertTrue(teamService.isTeamIdExists(1),
				"isTeamIdExists(teamId) is not working as expected for valid team id.");
	}

	@Test
	public void checkInvalidTeamIdExist() {
		assertFalse(teamService.isTeamIdExists(0),
				"isTeamIdExists(teamId) is not working as expected for invalid team id.");
	}

	@Test
	public void fetchAllTeamsTest() {
		assertTrue(teamService.fetchAllTeams().size() == 20, "fetchAllTeam() is not working as expected.");
	}
}
