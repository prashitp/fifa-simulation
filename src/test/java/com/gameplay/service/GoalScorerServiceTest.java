package com.gameplay.service;

import com.Constants;
import com.gameplay.service.GoalScorerService;
import com.models.PlayerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class GoalScorerServiceTest {
	GoalScorerService goalScorerService = new GoalScorerService();
	@Test
	void getPlayerForPenaltyTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[2]);
		players.add(Constants.PLAYERS[4]);
		PlayerModel player = goalScorerService.getPlayerForPenalty(players);
		assertEquals(Constants.PLAYERS[2].club,player.club);
	}

	@Test
	void getScorerForTypeTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[6]);
		players.add(Constants.PLAYERS[7]);
		players.add(Constants.PLAYERS[34]);
		PlayerModel player = goalScorerService.getScorerForType(Constants.FREE_KICK_SKILLS,Constants.ATTACKING_POSITIONS,players);
		assertEquals(Constants.PLAYERS[7].club,player.club);
	}
}