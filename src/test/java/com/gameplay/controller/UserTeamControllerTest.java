package com.gameplay.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.service.IUserTeamService;
import com.gameplay.service.UserTeamService;
import com.models.UserTeamModel;

public class UserTeamControllerTest {

	private IUserTeamService userTeamServiceMock = Mockito.mock(UserTeamService.class);
	private IUserTeamController userTeamController = new UserTeamController();

	private UserTeamModel userTeam = new UserTeamModel();

	@BeforeEach
	public void init() throws Exception {
		FieldSetter userTeamService = new FieldSetter(userTeamController,
				UserTeamController.class.getDeclaredField("userTeamService"));
		userTeamService.set(userTeamServiceMock);
	}

	@Test
	public void setUserTeamTest() {
		Mockito.when(userTeamServiceMock.setUserTeam(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userTeamController.setUserTeam(1), "setUserTeam() method is not working as expected.");
	}

	@Test
	public void getUserTeamTest() {
		Mockito.when(userTeamServiceMock.getUserTeam()).thenReturn(userTeam);
		assertTrue(userTeamController.fetchUserTeamModel() == userTeam,
				"fetchUserTeamModel() method is not working as expected.");
	}

	@Test
	public void deleteUserTeamTest() {
		Mockito.when(userTeamServiceMock.deleteUserTeam()).thenReturn(Boolean.TRUE);
		assertTrue(userTeamController.deleteUserTeamModel(), "deleteUserTeam() method is not working as expected.");
	}

	@Test
	public void customizePlayingXITest() {
		Mockito.when(userTeamServiceMock.customizePlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userTeamController.customizePlayingXI(Boolean.TRUE),
				"customizePlayingXI() method is not working as expected.");
	}
}
