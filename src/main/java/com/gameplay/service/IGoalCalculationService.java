package com.gameplay.service;

import com.models.ClubModel;

/**
 * @author prashitpatel
 */
public interface IGoalCalculationService {
	void addPenaltyGoals(ClubModel club, int penalties);

	void calculateNoOfGoals(ClubModel club, double probability);

	void addUpset(ClubModel homeClub, ClubModel awayClub);
}
