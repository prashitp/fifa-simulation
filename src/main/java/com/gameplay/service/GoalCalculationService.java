package com.gameplay.service;

import com.models.ClubModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/**
 * @author prashitpatel
 */
public class GoalCalculationService implements IGoalCalculationService {
	private static HashMap<ClubModel, Integer> scoreLine;

	public void addPenaltyGoals(ClubModel club, int penalties) {
		int penaltiesScored=0;
		for(int index=0; index<penalties; index++){
			if(Math.random() > 0.2) {
				penaltiesScored++;
			}
		}
		updateGoals(club, penaltiesScored);
	}

	public void calculateNoOfGoals(ClubModel club, double probability) {
		List<Double> probabilityMatcher = Arrays.asList(0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9);
		List<Integer> upperRange = Arrays.asList(0,0,1,2,2,3,3,4,4,5,5,6);
		List<Integer> lowerRange = Arrays.asList(0,0,0,0,0,1,1,2,2,3,4,4);

		int index = Math.max(0,probabilityMatcher.indexOf(probability));
		int goals = generateRandom(lowerRange.get(index), upperRange.get(index));
		updateGoals(club, goals);
	}

	private void updateGoals(ClubModel club, int goals) {
		if(scoreLine.containsKey(club)) {
			scoreLine.put(club,scoreLine.get(club) + goals);
		} else {
			scoreLine.put(club,goals);
		}
	}

	private int generateRandom(int lowerBound, int upperBound) {
		Random random = new Random();
		return (int) Math.round(random.nextDouble() * (upperBound - lowerBound) + lowerBound);
	}

	public static void resetScoreLine() {
		scoreLine = new HashMap<>();
	}

	public static HashMap<ClubModel, Integer> getScoreLine() {
		return scoreLine;
	}

	public void addUpset(ClubModel homeClub, ClubModel awayClub) {
		//swapping goals for upset;
		int homeGoals = scoreLine.get(homeClub) + scoreLine.get(awayClub);
		int awayGoals = scoreLine.get(homeClub) - scoreLine.get(awayClub);
		homeGoals = homeGoals - awayGoals;

		int goalDifference = Math.abs(homeGoals - awayGoals);
		int deductGoals = 0;
		if(homeGoals > awayGoals) {
			deductGoals = -generateRandom(awayGoals, goalDifference);
			updateGoals(homeClub, deductGoals);
		} else {
			deductGoals = -generateRandom(homeGoals, goalDifference);
			updateGoals(awayClub, deductGoals);
		}
	}
}
