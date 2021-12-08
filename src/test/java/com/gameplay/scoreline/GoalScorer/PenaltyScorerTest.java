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
class PenaltyScorerTest {
	Scorer penaltyScorer = new PenaltyScorer();

	@Test
	void getScorerTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[2]);
		players.add(Constants.PLAYERS[4]);
		PlayerModel player = penaltyScorer.getScorer(players);
		assertEquals(Constants.PLAYERS[2].club,player.club);
	}
}