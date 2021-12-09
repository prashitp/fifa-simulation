package com.gameplay.scoreline.ProbabilityCalculator;

import com.utils.Constants;
import com.gameplay.controller.ProbabilityController;
import com.gameplay.controller.TeamSelectionController;
import com.models.ClubModel;
import com.models.Criteria;
import com.models.Lineup;
import com.models.SetPieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class ProbabilityControllerTest {
	static ProbabilityController probabilityController;
	ProbabilityCriteria playerAttributeProbabilityCalculator = Mockito.mock(PlayerAttributeProbability.class);
	ProbabilityCriteria clubProbabilityCalculator = Mockito.mock(ClubAttributeProbability.class);
	ProbabilityCriteria formationProbabilityCalculator = Mockito.mock(FormationCriteriaProbability.class);
	ProbabilityCriteria setPieceProbabilityCalculator = Mockito.mock(SetPieceProbability.class);
	static List<Lineup> lineups = new ArrayList<>();

	@BeforeAll
	public static void init() {
		probabilityController = ProbabilityController.getInstance();
		TeamSelectionController teamSelectionController = new TeamSelectionController(Constants.CLUBS[0], Constants.CLUBS[1]);
		lineups = teamSelectionController.getSquads();

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
		probabilityController.setParams(Constants.CLUBS[0], Constants.CLUBS[1], lineups, setPieces);
	}

	@Test
	void getProbabilityTest() {
		HashMap<ClubModel, Double> probability = new HashMap<>();
		probability.put(Constants.CLUBS[0], 0.5);
		Mockito.when(playerAttributeProbabilityCalculator.getProbability()).thenReturn(probability);
		Mockito.when(clubProbabilityCalculator.getProbability()).thenReturn(probability);
		Mockito.when(formationProbabilityCalculator.getProbability()).thenReturn(probability);
		Mockito.when(setPieceProbabilityCalculator.getProbability()).thenReturn(probability);
		HashMap<Criteria, HashMap<ClubModel, Double>> probabilities = probabilityController.getProbabilities();
		int length = probabilities.size();
		assertEquals(4,length);
	}

	@Test
	void getGoalScorerProbabilitiesTest() {
		HashMap<ClubModel, Double> probability = new HashMap<>();
		probability.put(Constants.CLUBS[0], 0.5);
		Mockito.when(playerAttributeProbabilityCalculator.getProbability()).thenReturn(probability);
		Mockito.when(clubProbabilityCalculator.getProbability()).thenReturn(probability);
		Mockito.when(formationProbabilityCalculator.getProbability()).thenReturn(probability);
		Mockito.when(setPieceProbabilityCalculator.getProbability()).thenReturn(probability);
		HashMap<Criteria, Double> probabilities = probabilityController.getGoalScorerProbabilities(Constants.CLUBS[0]);
		int length = probabilities.size();
		assertEquals(3,length);
	}

}