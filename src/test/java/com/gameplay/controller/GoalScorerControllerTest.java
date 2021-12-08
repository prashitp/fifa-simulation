package com.gameplay.controller;

import com.Constants;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.gameplay.controller.GoalScorerController;
import com.gameplay.controller.IGoalScorerController;
import com.models.GoalType;
import com.models.Lineup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class GoalScorerControllerTest {
	static IGoalScorerController goalScorerController;

	@BeforeAll
	public static void init() {
		TeamSelectionController teamSelectionController = new TeamSelectionController("Arsenal","Aston Villa");
		List<Lineup> lineups = teamSelectionController.getSquads();
		goalScorerController = new GoalScorerController(lineups);
	}

	@Test
	void calculateGoalScorerTest() {
		goalScorerController.calculateGoalScorer(Constants.CLUBS[0], GoalType.OPEN_PLAY, Constants.CLUBS[1]);
		GoalScorerController.resetGoalScorers();
		assertEquals(0,GoalScorerController.getGoalScorers().size());
	}

	@Test
	void getGoalScorersTest() {
		GoalScorerController.resetGoalScorers();
		assertEquals(0,GoalScorerController.getGoalScorers().size());
	}

	@Test
	void resetGoalScorersTest() {
		GoalScorerController.resetGoalScorers();
		assertEquals(0,GoalScorerController.getGoalScorers().size());
	}
}