package com.gameplay.controller;

import com.models.ClubModel;
import com.models.GoalType;
/**
 * @author prashitpatel
 */
public interface IGoalScorerController {
	void calculateGoalScorer(ClubModel club, GoalType goalType, ClubModel awayClub);
}
