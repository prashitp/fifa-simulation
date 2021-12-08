package com.gameplay.service;

import com.Constants;
import com.gameplay.service.GoalCalculationService;
import com.gameplay.service.IGoalCalculationService;
import com.models.ClubModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class GoalCalculationServiceTest {
	IGoalCalculationService goalCalculationService = new GoalCalculationService();
	ClubModel club = Constants.CLUBS[0];
	ClubModel awayClub = Constants.CLUBS[1];
	@BeforeAll
	public static void init() {
		GoalCalculationService.resetScoreLine();
	}
	@Test
	void addPenaltyGoalsTest() {
		GoalCalculationService.resetScoreLine();
		goalCalculationService.addPenaltyGoals(club,2);
		HashMap<ClubModel, Integer> scoreLine = GoalCalculationService.getScoreLine();
		int scoreLineSize =  scoreLine.keySet().stream().collect(Collectors.toList()).size();
		assertEquals(1, scoreLineSize);
	}

	@Test
	void calculateNoOfGoalsTest() {
		GoalCalculationService.resetScoreLine();
		goalCalculationService.calculateNoOfGoals(club,0.5);
		HashMap<ClubModel, Integer> scoreLine = GoalCalculationService.getScoreLine();
		int scoreLineSize =  scoreLine.keySet().stream().collect(Collectors.toList()).size();
		assertEquals(1, scoreLineSize);
	}

	@Test
	void resetScoreLineTest() {
		GoalCalculationService.resetScoreLine();
		HashMap<ClubModel, Integer> scoreLine = GoalCalculationService.getScoreLine();
		assertEquals(0,scoreLine.size());
	}

	@Test
	void getScoreLineTest() {
		GoalCalculationService.resetScoreLine();
		HashMap<ClubModel, Integer> scoreLine = GoalCalculationService.getScoreLine();
		assertEquals(0,scoreLine.size());
	}

	@Test
	void addUpsetTest() {
		GoalCalculationService.resetScoreLine();
		goalCalculationService.calculateNoOfGoals(club,0.9);
		goalCalculationService.calculateNoOfGoals(awayClub,0.9);
		goalCalculationService.addUpset(club,awayClub);
		HashMap<ClubModel, Integer> scoreLine = GoalCalculationService.getScoreLine();
		int scoreLineSize =  scoreLine.keySet().stream().collect(Collectors.toList()).size();
		assertEquals(2, scoreLineSize);
	}
}