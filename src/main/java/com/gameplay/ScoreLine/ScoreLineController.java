package com.gameplay.ScoreLine;

import com.Constants;
import com.gameplay.ScoreLine.GoalCalculator.GoalCalculationController;
import com.gameplay.ScoreLine.GoalCalculator.IGoalCalculationController;
import com.models.ClubModel;
import com.models.SetPieceType;
import com.models.gameplay.TeamSelection.Lineup;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class ScoreLineController implements IScoreLineCalculator {
	ClubModel homeClub;
	ClubModel awayClub;
	List<Lineup> lineups;
	HashMap<SetPieceType, List<Integer>> setPieces;

	public ScoreLineController(String homeClub, String awayClub, List<Lineup> lineups, HashMap<SetPieceType, List<Integer>> setPieces) {
		this.homeClub = Arrays.stream(Constants.CLUBS).filter(club -> club.getClubName().equals(homeClub)).toArray(ClubModel[]::new)[0];
		this.awayClub = Arrays.stream(Constants.CLUBS).filter(club -> club.getClubName().equals(awayClub)).toArray(ClubModel[]::new)[0];
		this.lineups = lineups;
		this.setPieces = setPieces;
	}

	public HashMap<ClubModel,Integer> getScoreLine() {
		IGoalCalculationController goalCalculationController = new GoalCalculationController(homeClub, awayClub, lineups, setPieces);
		HashMap<ClubModel,Integer> scoreLine = goalCalculationController.getScoreLine();

		return scoreLine;
	}
}
