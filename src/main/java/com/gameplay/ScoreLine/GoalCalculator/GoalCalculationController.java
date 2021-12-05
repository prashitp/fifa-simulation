package com.gameplay.ScoreLine.GoalCalculator;

import com.models.ClubModel;
import com.models.gameplay.TeamSelection.Lineup;
import java.text.DecimalFormat;
import java.util.*;
/**
 * @author prashitpatel
 */
public class GoalCalculationController implements IGoalCalculationController {

	ClubModel homeClub;
	ClubModel awayClub;
	List<Lineup> lineups;
	List<HashMap<String, Integer>> setPieces;

	public GoalCalculationController(ClubModel homeClub, ClubModel awayClub, List<Lineup> lineups, List<HashMap<String, Integer>> setPieces) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.setPieces = setPieces;
		this.lineups = lineups;
	}

	public HashMap<ClubModel, Integer> getScoreLine() {
		List<Double> clubAttributeProbability;
		List<Double> formationProbability;
		List<Double> playerAttributeProbability;
		List<Double> setPieceProbability;

		IProbabilityCalculator playerAttributeProbabilityCalculator = new PlayerAttributeProbability(lineups.get(0).getPlaying11(), lineups.get(1).getPlaying11());
		playerAttributeProbability = playerAttributeProbabilityCalculator.getProbability();

		IProbabilityCalculator clubProbabilityCalculator = new ClubAttributeProbability(homeClub, awayClub);
		clubAttributeProbability = clubProbabilityCalculator.getProbability();

		IProbabilityCalculator formationProbabilityCalculator = new FormationCriteriaProbability(lineups.get(0).getFormation(), lineups.get(1).getFormation());
		formationProbability = formationProbabilityCalculator.getProbability();

		IProbabilityCalculator setPieceProbabilityCalculator = new SetPieceProbability(setPieces.get(0), setPieces.get(1));
		setPieceProbability = setPieceProbabilityCalculator.getProbability();

		double totalHomeProbability = (playerAttributeProbability.get(0) + clubAttributeProbability.get(0) +
				formationProbability.get(0) + setPieceProbability.get(0)) / 4;
		double totalAwayProbability = (playerAttributeProbability.get(1) + clubAttributeProbability.get(1) +
				formationProbability.get(1) + setPieceProbability.get(1)) / 4;

		return calculateGoals(totalHomeProbability, totalAwayProbability);
	}

	private HashMap<ClubModel, Integer> calculateGoals(double homeProbability, double awayProbability) {
		HashMap<ClubModel, Integer> scoreLine = new HashMap<>();

		DecimalFormat value = new DecimalFormat("#.#");
		homeProbability = Double.parseDouble(value.format(homeProbability));
		awayProbability = Double.parseDouble(value.format(awayProbability));

		int homeGoals = calculateRandomNoForGoal(homeProbability);
		int awayGoals = calculateRandomNoForGoal(awayProbability);

		if(Math.random() > 0.8) {
			//swapping goals for upset;
			homeGoals = homeGoals + awayGoals;
			awayGoals = homeGoals - awayGoals;
			homeGoals = homeGoals - awayGoals;

			int goalDifference = Math.abs(homeGoals - awayGoals);
			if(homeGoals > awayGoals) {
				homeGoals -= generateRandomDouble(awayGoals, goalDifference);;
			} else {
				awayGoals -= generateRandomDouble(homeGoals, goalDifference);;;
			}
		}

		int homePenalties = setPieces.get(0).get("PENALTY");
		int awayPenalties = setPieces.get(1).get("PENALTY");
		if(homePenalties > 0) {
			homeGoals += getPenaltyGoals(homePenalties);
		}
		if(awayPenalties > 0) {
			awayGoals += getPenaltyGoals(homePenalties);
		}
		scoreLine.put(homeClub,homeGoals);
		scoreLine.put(awayClub,awayGoals);

		return scoreLine;
	}

	private int getPenaltyGoals(int penalties) {
		int penaltiesScored=0;
		for(int index=0; index<penalties; index++){
			if(Math.random() > 0.2) {
				penaltiesScored++;
			}
		}
		return penaltiesScored;
	}

	private int calculateRandomNoForGoal(double probability) {
		List<Double> probabilityMatcher = Arrays.asList(0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9);
		List<Integer> upperRange = Arrays.asList(0,0,1,2,2,3,3,4,4,5,5,6);
		List<Integer> lowerRange = Arrays.asList(0,0,0,0,0,1,1,2,2,3,4,4);

		int index = probabilityMatcher.indexOf(probability);
		return generateRandomDouble(lowerRange.get(index), upperRange.get(index));
	}

	private int generateRandomDouble(int lowerBound, int upperBound) {
		Random random = new Random();
		return (int) Math.round(random.nextDouble() * (upperBound - lowerBound) + lowerBound);
	}
}
