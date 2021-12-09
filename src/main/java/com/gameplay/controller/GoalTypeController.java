package com.gameplay.controller;

import com.gameplay.service.GoalTypeService;
import com.gameplay.service.IGoalTypeService;
import com.models.ClubModel;
import com.models.Criteria;
import com.models.GoalType;
import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author prashitpatel
 */
public class GoalTypeController implements IGoalTypeController {

	HashMap<SetPieceType, List<Integer>> setPieces;
	HashMap<ClubModel, HashMap<SetPieceType, Integer>> setPiecesInput = new HashMap<>();
	IGoalScorerController goalScorerController;
	ClubModel homeClub;
	ClubModel awayClub;
	HashMap<ClubModel,Integer> scores;

	IGoalTypeService goalTypeService = new GoalTypeService();

	public GoalTypeController(ClubModel homeClub, ClubModel awayClub, HashMap<ClubModel,Integer> scores,
							  HashMap<SetPieceType, List<Integer>> setPieces, IGoalScorerController goalScorerController) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.scores = scores;
		this.setPieces = setPieces;
		this.goalScorerController = goalScorerController;
	}

	public void calculateGoalType() {
		GoalScorerController.resetGoalScorers();
		prepareSetPiecesInput();

		calculateGoalTypeClub(homeClub,scores.get(homeClub), awayClub);
		calculateGoalTypeClub(awayClub,scores.get(awayClub), homeClub);
	}

	private void calculateGoalTypeClub(ClubModel club, int goals, ClubModel opposingClub) {
		ProbabilityController probabilityController = ProbabilityController.getInstance();
		HashMap<Criteria, Double> clubProbabilities = probabilityController.getGoalScorerProbabilities(club);

		List<Map.Entry<Criteria, Double>> sortedProbabilities = goalTypeService.sortCriteriaHashMap(clubProbabilities);
		club.goals += goals;
		while(goals>0) {
			GoalType goalType;
			if(clubProbabilities.containsKey(Criteria.PENALTY) && clubProbabilities.get(Criteria.PENALTY) == 1.0) {
				goalType = goalTypeService.mapCriteriaToGoalType(Criteria.PENALTY);
				goalScorerController.calculateGoalScorer(club, goalType, opposingClub);
				clubProbabilities.put(Criteria.PENALTY, 0.00);
			} else {
				int index = goalTypeService.generateWeightedIndex();

				if(sortedProbabilities.get(index).getKey().equals(Criteria.PLAYER_ATTRIBUTES)) {
					goalType = goalTypeService.mapCriteriaToGoalType(sortedProbabilities.get(index).getKey());
					goalScorerController.calculateGoalScorer(club, goalType, opposingClub);
				} else {
					List<Map.Entry<SetPieceType, Integer>> sortedSetPiece = goalTypeService.sortSetPieceHashMap(setPiecesInput.get(club));
					index = goalTypeService.generateWeightedIndex();
					goalType = goalTypeService.mapSetPieceToGoalType(sortedSetPiece.get(index).getKey());
					goalScorerController.calculateGoalScorer(club, goalType ,opposingClub);
				}
			}
			goals--;
		}
	}

	private void prepareSetPiecesInput() {
		HashMap<SetPieceType, Integer> homeSetPieces = new HashMap<>();
		homeSetPieces.put(SetPieceType.CORNER_KICK, setPieces.get(SetPieceType.CORNER_KICK).get(0));
		homeSetPieces.put(SetPieceType.FREE_KICK, setPieces.get(SetPieceType.CORNER_KICK).get(0));

		HashMap<SetPieceType, Integer> awaySetPieces = new HashMap<>();
		awaySetPieces.put(SetPieceType.CORNER_KICK, setPieces.get(SetPieceType.CORNER_KICK).get(1));
		awaySetPieces.put(SetPieceType.FREE_KICK, setPieces.get(SetPieceType.CORNER_KICK).get(1));

		setPiecesInput.put(homeClub,homeSetPieces);
		setPiecesInput.put(awayClub,awaySetPieces);
	}
}
