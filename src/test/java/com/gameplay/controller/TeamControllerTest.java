package com.gameplay.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.service.ITeamService;
import com.gameplay.service.TeamService;
import com.models.ClubModel;

/**
 * @author Jay Patel
 */
public class TeamControllerTest {

	private ITeamService teamServiceMock = Mockito.mock(TeamService.class);
	private ITeamController teamController = new TeamController();

	private ClubModel team = new ClubModel(1, "Liverpool", null, 0);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter teamService = new FieldSetter(teamController,
				TeamController.class.getDeclaredField("teamService"));
		teamService.set(teamServiceMock);
	}

	@Test
	public void fetchAllTeamTest() {
		Mockito.when(teamServiceMock.fetchAllTeams()).thenReturn(Arrays.asList(team));
		assertTrue(teamController.fetchAllTeams().size() == 1, "fetctAllTeam() method is not working as expected.");
	}

	@Test
	public void isTeamIdExistsTest() {
		Mockito.when(teamServiceMock.isTeamIdExists(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(teamController.isTeamIdExists(1), "isTeamIdExists() method is not working as expected.");
	}
}
