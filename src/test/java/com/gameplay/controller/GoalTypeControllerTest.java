package com.gameplay.controller;

import com.Constants;
import com.gameplay.controller.GoalTypeController;
import com.gameplay.controller.IGoalTypeController;
import com.gameplay.controller.GoalScorerController;
import com.gameplay.controller.IGoalScorerController;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.models.ClubModel;
import com.models.SetPieceType;
import com.models.Goal;
import com.models.Lineup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class GoalTypeControllerTest {
	static IGoalTypeController goalTypeController;
	@BeforeAll
	public static void init() {
		HashMap<ClubModel,Integer> scores = new HashMap<>();
		scores.put(Constants.CLUBS[0], 1);
		scores.put(Constants.CLUBS[1], 1);

		TeamSelectionController teamSelectionController = new TeamSelectionController("Chelsea","Arsenal");
		List<Lineup> lineups = teamSelectionController.getSquads();
		IGoalScorerController goalScorerController = new GoalScorerController(lineups);

		HashMap<SetPieceType, List<Integer>> setPieces = new HashMap<>();
		List<Integer> freeKicks = new ArrayList<>();
		List<Integer> corners = new ArrayList<>();
		List<Integer> penalty = new ArrayList<>();

		freeKicks.add(15);
		freeKicks.add(10);

		corners.add(12);
		corners.add(7);

		penalty.add(0);
		penalty.add(1);

		setPieces.put(SetPieceType.FREE_KICK,freeKicks);
		setPieces.put(SetPieceType.CORNER_KICK,corners);
		setPieces.put(SetPieceType.PENALTY_KICK,penalty);
		goalTypeController = new GoalTypeController(Constants.CLUBS[0], Constants.CLUBS[1], scores, setPieces, goalScorerController );
	}
	@Test
	void calculateGoalTypeTest() {
		GoalScorerController.resetGoalScorers();
		HashMap<ClubModel, List<Goal>> goalScorers= GoalScorerController.getGoalScorers();
		assertEquals(0,goalScorers.size());
	}
}