package com.gameplay.scoreline.GoalScorer;

import com.utils.Constants;
import com.models.PlayerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class OpenPlayScorerTest {
	Scorer openPlayScorer = new FreeKickScorer();
	@Test
	void getScorerTest() {
		List<PlayerModel> players = new ArrayList<>();
		players.add(Constants.PLAYERS[6]);
		players.add(Constants.PLAYERS[7]);
		players.add(Constants.PLAYERS[34]);
		PlayerModel player = openPlayScorer.getScorer(players);
		assertEquals(Constants.PLAYERS[7].club,player.club);
	}
}