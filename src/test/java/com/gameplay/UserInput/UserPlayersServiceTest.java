package com.gameplay.UserInput;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public class UserPlayersServiceTest {

	private IUserPlayersService userPlayersService = new UserPlayersService();

	private IUserPlayersRepository userPlayerRepositoryMock = Mockito.mock(UserPlayersRepository.class);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter setter = new FieldSetter(userPlayersService,
				UserPlayersService.class.getDeclaredField("userPlayerRepository"));
		setter.set(userPlayerRepositoryMock);
	}

	@Test
	public void selectPlayerTest() {
		Mockito.when(userPlayerRepositoryMock.selectPlayer(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userPlayersService.selectPlayer(1), "selectPlayer() method is not working properly as expected!");
	}

	@Test
	public void setUsersPlayingXITest() {
		PlayerEntity player = new PlayerEntity();
		player.setPlayerId("P001");
		Mockito.when(userPlayerRepositoryMock.selectPlayer(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userPlayersService.setUsersPlayingXI(Arrays.asList(player)),
				"setUsersPlayingXI() method is not working as expected.");
	}

	@Test
	public void setUsersPlayingXIInvalidTest() {
		Mockito.when(userPlayerRepositoryMock.selectPlayer(Mockito.any())).thenThrow(new RuntimeException());
		assertFalse(userPlayersService.setUsersPlayingXI(Arrays.asList(new PlayerEntity())),
				"setUsersPlayingXI() method is not working as expected.");
	}
}
