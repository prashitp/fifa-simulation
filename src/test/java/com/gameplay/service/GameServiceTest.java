package com.gameplay.service;

import com.models.ClubModel;
import com.models.PlayerModel;
import com.utils.Constants;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
	GameService gameService = new GameService();

	@Test
	void getHighestGoalScorerTest() {
		Constants.PLAYERS[0].goals=1;
		HashMap<PlayerModel,Integer> map = gameService.getHighestGoalScorer();
		assertEquals(1,map.size());
	}

	@Test
	void getHighestGoalsByClub() {
		Constants.CLUBS[0].goals=1;
		HashMap<ClubModel,Integer> map = gameService.getHighestGoalsByClub();
		assertEquals(1,map.size());
	}

	@Test
	void resetPlayerGoals() {
		gameService.resetPlayerGoals();
		assertEquals(0,Constants.PLAYERS[0].goals);
	}

	@Test
	void resetClubGoals() {
		gameService.resetClubGoals();
		assertEquals(0,Constants.CLUBS[0].goals);
	}

}