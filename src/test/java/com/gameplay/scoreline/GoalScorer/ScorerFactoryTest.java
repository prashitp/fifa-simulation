package com.gameplay.scoreline.GoalScorer;

import com.models.GoalType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author prashitpatel
 */
class ScorerFactoryTest {

	ScorerFactory scorerFactory = new ScorerFactory();
	@Test
	void getScorerTypeOwnGoalTest() {
		Scorer scorer = scorerFactory.getScorerType(GoalType.OWN_GOAL);
		assertNotNull(scorer);
	}

	@Test
	void getScorerTypeCornerTest() {
		Scorer scorer = scorerFactory.getScorerType(GoalType.CORNER);
		assertNotNull(scorer);
	}

	@Test
	void getScorerTypeFreeKickTest() {
		Scorer scorer = scorerFactory.getScorerType(GoalType.FREE_KICK);
		assertNotNull(scorer);
	}

	@Test
	void getScorerTypePenaltyTest() {
		Scorer scorer = scorerFactory.getScorerType(GoalType.PENALTY);
		assertNotNull(scorer);
	}

	@Test
	void getScorerTypeOpenPlayTest() {
		Scorer scorer = scorerFactory.getScorerType(GoalType.OPEN_PLAY);
		assertNotNull(scorer);
	}
}