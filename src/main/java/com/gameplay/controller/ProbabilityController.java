package com.gameplay.controller;

import com.gameplay.scoreline.ProbabilityCalculator.*;
import com.models.ClubModel;
import com.models.SetPieceType;
import com.models.Criteria;
import com.models.Lineup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class ProbabilityController {
	private static ClubModel homeClub;
	private static ClubModel awayClub;
	private static List<Lineup> lineups;
	private static HashMap<SetPieceType,List<Integer>> setPieces;
	HashMap<Criteria, HashMap<ClubModel, Double>> probabilities;

	private static ProbabilityController instance = null;
	public static ProbabilityController getInstance() {
		if(instance == null) {
			return new ProbabilityController();
		}
		return instance;
	}

	public void setParams(ClubModel homeClub, ClubModel awayClub, List<Lineup> lineups,HashMap<SetPieceType,List<Integer>> setPieces) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.lineups = lineups;
		this.setPieces = setPieces;
	}

	public HashMap<Criteria, HashMap<ClubModel, Double>> getProbabilities() {
		probabilities = new HashMap<>();
		probabilities.put(Criteria.PLAYER_ATTRIBUTES, getPlayerAttributeProbability(lineups));
		probabilities.put(Criteria.CLUB_ATTRIBUTES, getClubAttributeProbability(homeClub, awayClub));
		probabilities.put(Criteria.FORMATION, getFormationProbability(lineups));
		probabilities.put(Criteria.SET_PIECES, getSetPieceProbability(setPieces));

		return probabilities;
	}

	public HashMap<Criteria, Double> getGoalScorerProbabilities(ClubModel club) {
		HashMap<Criteria, Double> clubProbabilities = new HashMap<>();
		clubProbabilities.put(Criteria.PLAYER_ATTRIBUTES, getPlayerAttributeProbability(lineups).get(club));
		clubProbabilities.put(Criteria.SET_PIECES, getSetPieceProbability(setPieces).get(club));
		clubProbabilities.put(Criteria.PENALTY, getPenaltyProbability().get(club));
		return clubProbabilities;
	}

	private HashMap<ClubModel, Double> getPlayerAttributeProbability(List<Lineup> lineups) {
		ProbabilityCriteria playerAttributeProbabilityCalculator = new PlayerAttributeProbability(homeClub, awayClub, lineups.get(0).getPlaying11(), lineups.get(1).getPlaying11());
		return playerAttributeProbabilityCalculator.getProbability();
	}

	private HashMap<ClubModel, Double> getClubAttributeProbability(ClubModel homeClub, ClubModel awayClub) {
		ProbabilityCriteria clubProbabilityCalculator = new ClubAttributeProbability(homeClub, awayClub);
		return clubProbabilityCalculator.getProbability();
	}

	private HashMap<ClubModel, Double> getFormationProbability(List<Lineup> lineups) {
		ProbabilityCriteria formationProbabilityCalculator = new FormationCriteriaProbability(homeClub, awayClub, lineups.get(0).getFormation(), lineups.get(1).getFormation());
		return formationProbabilityCalculator.getProbability();
	}

	private HashMap<ClubModel, Double> getSetPieceProbability(HashMap<SetPieceType,List<Integer>> setPiecesInput) {
		List<HashMap<SetPieceType, Integer>> setPieces = prepareSetPieceInput(setPiecesInput);
		ProbabilityCriteria setPieceProbabilityCalculator = new SetPieceProbability(homeClub, awayClub, setPieces.get(0), setPieces.get(1));
		return setPieceProbabilityCalculator.getProbability();
	}

	private HashMap<ClubModel, Double> getPenaltyProbability() {
		HashMap<ClubModel, Double> penaltyProbability = new HashMap<>();

		penaltyProbability.put(homeClub, 0.00);
		penaltyProbability.put(awayClub, 0.00);
		if(setPieces.get(SetPieceType.PENALTY_KICK).get(0) > 0) {
			penaltyProbability.put(homeClub, 1.00);
		}
		if(setPieces.get(SetPieceType.PENALTY_KICK).get(1) > 0) {
			penaltyProbability.put(awayClub, 1.00);
		}
		return penaltyProbability;
	}

	private List<HashMap<SetPieceType, Integer>> prepareSetPieceInput(HashMap<SetPieceType,List<Integer>> setPieces) {
		HashMap<SetPieceType, Integer> homeSetPieces = new HashMap<>();
		HashMap<SetPieceType, Integer> awaySetPieces = new HashMap<>();

		for (SetPieceType setPieceType: setPieces.keySet()) {
			homeSetPieces.put(setPieceType, setPieces.get(setPieceType).get(0));
			awaySetPieces.put(setPieceType, setPieces.get(setPieceType).get(1));
		}

		List<HashMap<SetPieceType, Integer>> setPieceInput = new ArrayList<>();
		setPieceInput.add(homeSetPieces);
		setPieceInput.add(awaySetPieces);

		return setPieceInput;
	}
}
