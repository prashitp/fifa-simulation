package com.models;

/**
 * @author prashitpatel
 */
public class Goal {
	private GoalType goalType;
	private PlayerModel goalScorer;

	public Goal(GoalType goalType, PlayerModel goalScorer){
		this.goalType = goalType;
		this.goalScorer = goalScorer;
	}

	public GoalType getGoalType() {
		return goalType;
	}

	public PlayerModel getGoalScorer() {
		return goalScorer;
	}
}
