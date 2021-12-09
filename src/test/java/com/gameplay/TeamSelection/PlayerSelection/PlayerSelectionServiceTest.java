package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import com.gameplay.service.PlayerSelectionService;
import com.models.PlayerModel;
import com.models.PlayerPositions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author prashitpatel
 */
class PlayerSelectionServiceTest {
	PlayerSelectionService playerSelectionService = new PlayerSelectionService();
	PlayerSelectionService playerSelectionServiceStatic;

	@Test
	void getInstanceTest() {
		playerSelectionServiceStatic = PlayerSelectionService.getInstance();
		assertNotNull(playerSelectionServiceStatic);
	}

	@Test
	void groupPlayersTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[0]);

		PlayerPositions[] playerPositions = {
				PlayerPositions.ST
		};

		List<PlayerModel> groupedPlayers = playerSelectionService.groupPlayers(players,playerPositions);
		assertEquals(groupedPlayers,players);
	}

	@Test
	void getMatchReadyPlayersTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[0]);
		int stamina = 10;
		List<PlayerModel> matchReadyPlayers = playerSelectionService.getMatchReadyPlayers(players,stamina);
		assertEquals(matchReadyPlayers,players);
	}

	@Test
	void sortPlayersTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[0]);
		players.add(Constants.PLAYERS[1]);

		List<PlayerModel> sortedPlayers = playerSelectionService.sortPlayers(players);
		assertEquals(players,sortedPlayers);
	}
}