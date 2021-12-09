package com.gameplay.service;

import com.gameplay.repository.IUserTeamRepository;
import com.gameplay.repository.UserTeamRepository;
import com.models.UserTeamModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jay Patel
 */
public class UserTeamServiceTest {

	private IUserTeamService userTeamService = new UserTeamService();

	private IUserTeamRepository userTeamRepositoryMock = Mockito.mock(UserTeamRepository.class);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter setter = new FieldSetter(userTeamService,
				UserTeamService.class.getDeclaredField("userTeamRepository"));
		setter.set(userTeamRepositoryMock);
	}

	@Test
	public void setUserTeamValidTest() {
		Mockito.when(userTeamRepositoryMock.setUserTeam(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userTeamService.setUserTeam(1), "setUserTeam(teamId) is not working as expected.");
	}

	@Test
	public void customizePlayingXIValidTest() {
		Mockito.when(userTeamRepositoryMock.customizePlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userTeamService.customizePlayingXI(Boolean.TRUE),
				"customizePlayingXI(flag) is not working as expected.");
	}

	@Test
	public void getUserTeamTest() {
		Mockito.when(userTeamRepositoryMock.fetchUserTeam()).thenReturn(new UserTeamModel());
		assertNotNull(userTeamService.getUserTeam(), "getUserTeam() method is not working as expected.");
	}

	@Test
	public void setSeasonPlayedTest() {
		Mockito.when(userTeamRepositoryMock.setSeasonPlayed(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userTeamService.setSeasonPlayed(1), "setSeasonPlayed() method is not working as expected.");
	}
}
