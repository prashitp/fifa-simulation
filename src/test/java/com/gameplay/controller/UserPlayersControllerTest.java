package com.gameplay.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.service.IUserPlayersService;
import com.gameplay.service.UserPlayersService;

/**
 * @author Jay Patel
 */
public class UserPlayersControllerTest {

	private IUserPlayersService userPlayersServiceMock = Mockito.mock(UserPlayersService.class);
	private IUserPlayersController userPlayersController = new UserPlayersController();

	@BeforeEach
	public void init() throws Exception {
		FieldSetter userPlayersService = new FieldSetter(userPlayersController,
				UserPlayersController.class.getDeclaredField("userPlayersService"));
		userPlayersService.set(userPlayersServiceMock);
	}

	@Test
	public void setUsersPlayingXITest() {
		Mockito.when(userPlayersServiceMock.setUsersPlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userPlayersController.setPlayingXI(null), "setPlayingXI() method is not working as expected.");
	}

}
