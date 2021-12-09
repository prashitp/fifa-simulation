package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author prashitpatel
 */
class PlayerCategoryFactoryTest {
	PlayerCategoryFactory playerCategoryFactory = new PlayerCategoryFactory();
	static List<PlayerModel> players;

	@BeforeAll
	public static void init() {
		players = new ArrayList<>();
		players.add(Constants.PLAYERS[0]);
	}

	@Test
	void getPlayerCategoryForwardTest() {
		IPlayerCategory playerCategory = playerCategoryFactory.getPlayerCategory(PlayingPosition.FORWARD,players);
		assertNotNull(playerCategory);
	}

	@Test
	void getPlayerCategoryDefenderTest() {
		IPlayerCategory playerCategory = playerCategoryFactory.getPlayerCategory(PlayingPosition.DEFENDER,players);
		assertNotNull(playerCategory);
	}

	@Test
	void getPlayerCategoryMidFielderTest() {
		IPlayerCategory playerCategory = playerCategoryFactory.getPlayerCategory(PlayingPosition.MIDFIELDER,players);
		assertNotNull(playerCategory);
	}

	@Test
	void getPlayerCategoryGoalKeeperTest() {
		IPlayerCategory playerCategory = playerCategoryFactory.getPlayerCategory(PlayingPosition.GOALKEEPER,players);
		assertNotNull(playerCategory);
	}
}