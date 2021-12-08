package com.models;

import com.Constants;
import com.models.Goal;
import com.models.GoalType;
import com.models.PlayerModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class GoalTest {
	Goal goal = new Goal(GoalType.OPEN_PLAY, Constants.PLAYERS[0]);
	@Test
	void getGoalType() {
		GoalType goalType = goal.getGoalType();
		assertEquals(GoalType.OPEN_PLAY,goalType);
	}

	@Test
	void getGoalScorerTest() {
		PlayerModel player = goal.getGoalScorer();
		assertEquals(Constants.PLAYERS[0], player);
	}
}