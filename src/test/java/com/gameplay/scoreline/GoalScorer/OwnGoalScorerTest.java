package com.gameplay.scoreline.GoalScorer;

import com.Constants;
import com.models.PlayerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class OwnGoalScorerTest {
	Scorer ownGoalScorer = new OwnGoalScorer();
	@Test
	void getScorerTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[15]);
		players.add(Constants.PLAYERS[16]);
		players.add(Constants.PLAYERS[38]);
		PlayerModel player = ownGoalScorer.getScorer(players);
		assertEquals(Constants.PLAYERS[16].club,player.club);
	}
}