package com.gameplay.UserInput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.Constants;
import com.database_operations.DatabaseImport;
import com.entity.PlayerEntity;
import com.gameplay.TeamSelection.ITeamSelectionController;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.io.IInputStream;
import com.io.IOutputStream;
import com.io.StandardInputStream;
import com.io.StandardOutputStream;
import com.models.UserTeamModel;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class UserInputServiceTest {

	private IUserInputService userInputService = new UserInputService();

	private ITeamService teamServiceMock = Mockito.mock(TeamService.class);

	private IPlayerStatusService playerStatusServiceMock = Mockito.mock(PlayerStatusService.class);

	private IUserTeamService userTeamServiceMock = Mockito.mock(UserTeamService.class);

	private IUserPlayersService userPlayersServiceMock = Mockito.mock(UserPlayersService.class);

	private IOutputStream outputStreamMock = Mockito.mock(StandardOutputStream.class);

	private IInputStream inputStreamMock = Mockito.mock(StandardInputStream.class);

	private static final String clubName = "Liverpool";
	private static final String opposingClubName = "Manchester United";
	private ITeamSelectionController teamSelectionController = new TeamSelectionController(clubName, opposingClubName);
	private IPlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();

	@BeforeEach
	public void init() throws Exception {
		FieldSetter teamService = new FieldSetter(userInputService,
				UserInputService.class.getDeclaredField("teamService"));
		teamService.set(teamServiceMock);

		FieldSetter playerStatusService = new FieldSetter(userInputService,
				UserInputService.class.getDeclaredField("playerStatusService"));
		playerStatusService.set(playerStatusServiceMock);

		FieldSetter userTeamService = new FieldSetter(userInputService,
				UserInputService.class.getDeclaredField("userTeamService"));
		userTeamService.set(userTeamServiceMock);

		FieldSetter userPlayerService = new FieldSetter(userInputService,
				UserInputService.class.getDeclaredField("userPlayersService"));
		userPlayerService.set(userPlayersServiceMock);

		FieldSetter outputStream = new FieldSetter(userInputService,
				UserInputService.class.getDeclaredField("outputStream"));
		outputStream.set(outputStreamMock);

		FieldSetter inputStream = new FieldSetter(userInputService,
				UserInputService.class.getDeclaredField("inputStream"));
		inputStream.set(inputStreamMock);
	}

	@Test
	@Order(1)
	public void selectTeamIdTest() {
		Mockito.when(teamServiceMock.fetchAllTeams()).thenReturn(Arrays.asList(DatabaseImport.getClubs()));
		Mockito.when(inputStreamMock.readInteger()).thenReturn(1);
		Mockito.when(teamServiceMock.isTeamIdExists(Mockito.any())).thenReturn(Boolean.TRUE);
		Mockito.when(userTeamServiceMock.setUserTeam(Mockito.any())).thenReturn(Boolean.TRUE);
		assertEquals(userInputService.selectTeamId(), 1, "selectTeamId() method is not working as expected.");
	}

	@Test
	@Order(2)
	public void selectTeamIdInvalidTest() {
		Mockito.when(inputStreamMock.readInteger()).thenReturn(1);
		Mockito.when(teamServiceMock.isTeamIdExists(Mockito.any())).thenReturn(Boolean.FALSE);
		assertEquals(userInputService.selectTeamId(), null, "selectTeamId() method is not working as expected.");
	}

	@Test
	@Order(3)
	public void startNewGameTest1() {
		UserTeamModel userTeam = new UserTeamModel();
		userTeam.setSeasonPlayed(Constants.TOTAL_SEASONS_PLAYED_IN_SIMULATION);
		Mockito.when(userTeamServiceMock.getUserTeam()).thenReturn(userTeam);
		Mockito.when(playerStatusServiceMock.deleteAllPlayers()).thenReturn(Boolean.TRUE);
		Mockito.when(playerStatusServiceMock.copyFromPlayerToPlayerStatus()).thenReturn(Boolean.TRUE);
		Mockito.when(userTeamServiceMock.customizePlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		Mockito.when(userTeamServiceMock.setSeasonPlayed(0)).thenReturn(Boolean.TRUE);
		userInputService.startNewGame();
	}

	@Test
	@Order(4)
	public void startNewGameTest2() {
		UserTeamModel userTeam = new UserTeamModel();
		userTeam.setSeasonPlayed(0);
		Mockito.when(userTeamServiceMock.getUserTeam()).thenReturn(userTeam);
		Mockito.when(inputStreamMock.readInteger()).thenReturn(1);
		Mockito.when(playerStatusServiceMock.deleteAllPlayers()).thenReturn(Boolean.TRUE);
		Mockito.when(userTeamServiceMock.deleteUserTeam()).thenReturn(Boolean.TRUE);
		Mockito.when(playerStatusServiceMock.copyFromPlayerToPlayerStatus()).thenReturn(Boolean.TRUE);
		Mockito.when(userTeamServiceMock.customizePlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		Mockito.when(userTeamServiceMock.setSeasonPlayed(0)).thenReturn(Boolean.TRUE);
		userInputService.startNewGame();
	}

	@Test
	@Order(5)
	public void resumeGameTest() {
		UserTeamModel userTeam = new UserTeamModel();
		userTeam.setSeasonPlayed(0);
		Mockito.when(userTeamServiceMock.getUserTeam()).thenReturn(userTeam);
		Mockito.when(inputStreamMock.readInteger()).thenReturn(3);
		Mockito.when(inputStreamMock.readInteger()).thenReturn(2);
		userInputService.startNewGame();
	}

	@Test
	@Order(6)
	public void customizePlayingXIYesTest() {
		Mockito.when(inputStreamMock.readInteger()).thenReturn(1);
		Mockito.when(userTeamServiceMock.customizePlayingXI(Mockito.any())).thenReturn(Boolean.TRUE);
		assertTrue(userInputService.customizePlayingXI(), "customizePlayingXI() method is not working as expected.");
	}

	@Test
	@Order(7)
	public void customizePlayingXINoTest() {
		Mockito.when(inputStreamMock.readInteger()).thenReturn(2);
		Mockito.when(userTeamServiceMock.customizePlayingXI(Mockito.any())).thenReturn(Boolean.FALSE);
		assertFalse(userInputService.customizePlayingXI(), "customizePlayingXI() method is not working as expected.");
	}

	@Test
	@Order(8)
	public void customizePlayingXIInvalidTest() {
		Mockito.when(inputStreamMock.readInteger()).thenReturn(3);
		assertNull(userInputService.customizePlayingXI(), "customizePlayingXI() method is not working as expected.");
	}

	@Test
	@Order(9)
	public void selectPlayingXIInvalidTest() {
		PlayerEntity player = new PlayerEntity();
		player.setPlayerId("P001");
		player.setPlayerName("xyz");
		Mockito.when(inputStreamMock.readInteger()).thenReturn(3, 1, 1, 2, 4, 1, 5);
		Mockito.when(playerStatusServiceMock.fetchPlayer(Mockito.anyInt())).thenReturn(player);
		Mockito.when(playerStatusServiceMock.fetchPlayer(Mockito.anyInt())).thenReturn(player);
		assertFalse(userInputService.selectPlayingXI(1), "selectPlayingXI() method is not working as expected.");
	}

	@Test
	@Order(10)
	public void selectPlayingXITest() {
		List<PlayerEntity> players = teamSelectionController.getTeam().getplaying11().entrySet().stream()
				.map(entrySet -> playerStatusRepository.fetchPlayer(entrySet.getKey().getPlayerId()))
				.collect(Collectors.toList());
		Mockito.when(inputStreamMock.readInteger()).thenReturn(3,
				Converter.convertToPlayerIdInteger(players.get(0).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(1).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(2).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(3).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(4).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(5).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(6).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(7).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(8).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(9).getPlayerId()), 3,
				Converter.convertToPlayerIdInteger(players.get(10).getPlayerId()), 5);
		Mockito.when(playerStatusServiceMock.fetchPlayer(Mockito.anyInt())).thenReturn(players.get(0), players.get(1),
				players.get(2), players.get(3), players.get(4), players.get(5), players.get(6), players.get(7),
				players.get(8), players.get(9), players.get(10));
		assertTrue(userInputService.selectPlayingXI(11), "selectPlayingXI() method is not working as expected.");
	}
}
