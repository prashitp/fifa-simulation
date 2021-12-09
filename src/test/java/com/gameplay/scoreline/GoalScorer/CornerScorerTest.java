package com.gameplay.scoreline.GoalScorer;

import com.utils.Constants;
import com.models.PlayerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CornerScorerTest {
	Scorer cornerScorer = new CornerScorer();
	@Test
	void getScorerTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[15]);
		players.add(Constants.PLAYERS[16]);
		players.add(Constants.PLAYERS[38]);
		PlayerModel player = cornerScorer.getScorer(players);
		assertEquals(Constants.PLAYERS[16].club,player.club);
	}
}