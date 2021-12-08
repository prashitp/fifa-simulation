package com.gameplay.scoreline.ProbabilityCalculator;

import com.models.ClubModel;

import java.util.HashMap;
	/**
	 * @author prashitpatel
	 */
public abstract class ProbabilityCriteria {
	ClubModel homeClub;
	ClubModel awayClub;

	public ProbabilityCriteria(ClubModel homeClub, ClubModel awayClub) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
	}
	public abstract HashMap<ClubModel, Double> getProbability();
}