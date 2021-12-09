package com.gameplay.controller;

import com.models.ClubModel;
import com.models.Goal;
import com.models.Lineup;
import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class ScoreLineController implements IScoreLineController {
	ClubModel homeClub;
	ClubModel awayClub;
	List<Lineup> lineups;
	HashMap<SetPieceType,List<Integer>> setPieces;

	public ScoreLineController(ClubModel homeClub, ClubModel awayClub, List<Lineup> lineups, HashMap<SetPieceType,List<Integer>> setPieces) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.lineups = lineups;
		this.setPieces = setPieces;
	}

	public HashMap<ClubModel, List<Goal>> getScoreLine() {
		IGoalCalculationController goalCalculationController = new GoalCalculationController(homeClub, awayClub, lineups, setPieces);
		HashMap<ClubModel,Integer> scoreLine = goalCalculationController.getScores();

		IGoalScorerController goalScorerController = new GoalScorerController(lineups);
		IGoalTypeController goalTypeController = new GoalTypeController(homeClub, awayClub, scoreLine, setPieces, goalScorerController);
		goalTypeController.calculateGoalType();

		return GoalScorerController.getGoalScorers();
	}
}
