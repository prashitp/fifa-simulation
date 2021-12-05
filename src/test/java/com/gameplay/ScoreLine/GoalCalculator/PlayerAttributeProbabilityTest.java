package com.gameplay.ScoreLine.GoalCalculator;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class PlayerAttributeProbabilityTest {
	static PlayerAttributeProbability playerAttributeProbability;

	@BeforeAll
	public static void init(){
		HashMap<PlayerModel, PlayingPosition> homeTeam = new HashMap<>();
		homeTeam.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
		homeTeam.put(Constants.PLAYERS[12], PlayingPosition.MIDFIELDER);
		homeTeam.put(Constants.PLAYERS[20], PlayingPosition.DEFENDER);
		homeTeam.put(Constants.PLAYERS[39], PlayingPosition.GOALKEEPER);

		HashMap<PlayerModel,PlayingPosition> awayTeam = new HashMap<>();
		awayTeam.put(Constants.PLAYERS[1], PlayingPosition.FORWARD);
		awayTeam.put(Constants.PLAYERS[8], PlayingPosition.MIDFIELDER);
		awayTeam.put(Constants.PLAYERS[13], PlayingPosition.DEFENDER);
		awayTeam.put(Constants.PLAYERS[18], PlayingPosition.GOALKEEPER);
		playerAttributeProbability = new PlayerAttributeProbability(homeTeam, awayTeam);
	}

	@Test
	void getProbabilityTest() {
		List<Double> probability = playerAttributeProbability.getProbability();
		assertEquals(2,probability.size());
	}
}