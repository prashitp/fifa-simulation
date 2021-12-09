package com.gameplay.controller;

import com.gameplay.service.GoalCalculationService;
import com.gameplay.service.IGoalCalculationService;
import com.models.ClubModel;
import com.models.SetPieceType;
import com.models.Criteria;
import com.models.Lineup;
import java.text.DecimalFormat;
import java.util.*;
/**
 * @author prashitpatel
 */
public class GoalCalculationController implements IGoalCalculationController {
	ClubModel homeClub;
	ClubModel awayClub;
	List<Lineup> lineups;
	HashMap<SetPieceType,List<Integer>> setPieces;

	public GoalCalculationController(ClubModel homeClub, ClubModel awayClub, List<Lineup> lineups, HashMap<SetPieceType,List<Integer>> setPieces) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.lineups = lineups;
		this.setPieces = setPieces;
	}

	public HashMap<ClubModel, Integer> getScores() {
		GoalCalculationService.resetScoreLine();

		ProbabilityController probabilityController = ProbabilityController.getInstance();
		probabilityController.setParams(homeClub, awayClub, lineups, setPieces);
		HashMap<Criteria, HashMap<ClubModel, Double>> probabilities = probabilityController.getProbabilities();

		double totalHomeProbability = calculateTotalProbabilities(probabilities, homeClub) / 4;
		double totalAwayProbability = calculateTotalProbabilities(probabilities, awayClub) / 4;

		calculateGoals(totalHomeProbability, totalAwayProbability);

		return GoalCalculationService.getScoreLine();
	}

	private double calculateTotalProbabilities(HashMap<Criteria, HashMap<ClubModel, Double>> probabilities, ClubModel club) {
		double totalProbability = 0;
		for (Criteria criteria:probabilities.keySet()) {
			totalProbability += probabilities.get(criteria).get(club);
		}
		return totalProbability;
	}

	private void calculateGoals(double homeProbability, double awayProbability) {
		IGoalCalculationService goalCalculationService = new GoalCalculationService();

		DecimalFormat value = new DecimalFormat("#.#");
		homeProbability = Double.parseDouble(value.format(homeProbability));
		awayProbability = Double.parseDouble(value.format(awayProbability));

		goalCalculationService.calculateNoOfGoals(homeClub, homeProbability);
		goalCalculationService.calculateNoOfGoals(awayClub, awayProbability);

		int homePenalties = setPieces.get(SetPieceType.PENALTY_KICK).get(0);
		int awayPenalties = setPieces.get(SetPieceType.PENALTY_KICK).get(1);

		goalCalculationService.addPenaltyGoals(homeClub,homePenalties);
		goalCalculationService.addPenaltyGoals(awayClub,awayPenalties);

		if(Math.random() > 0.8) {
			goalCalculationService.addUpset(homeClub, awayClub);
		}
	}
}
