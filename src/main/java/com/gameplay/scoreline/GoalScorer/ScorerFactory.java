package com.gameplay.scoreline.GoalScorer;

import com.models.GoalType;
/**
 * @author prashitpatel
 */
public class ScorerFactory {
	public Scorer getScorerType(GoalType goalType) {
		if(goalType.equals(GoalType.OWN_GOAL)) {
			return new OwnGoalScorer();
	 	} else if (goalType.equals(GoalType.CORNER)) {
			return new CornerScorer();
		} else if (goalType.equals(GoalType.FREE_KICK)) {
			return new FreeKickScorer();
		} else if (goalType.equals(GoalType.PENALTY)) {
			return new PenaltyScorer();
		} else {
			return new OpenPlayScorer();
		}
	}
}
