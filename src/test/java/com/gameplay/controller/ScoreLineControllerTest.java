package com.gameplay.controller;

import com.utils.Constants;
import com.models.ClubModel;
import com.models.Goal;
import com.models.Lineup;
import com.models.SetPieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author prashitpatel
 */
class ScoreLineControllerTest {
	static ScoreLineController scoreLineController;

	@BeforeAll
	public static void init() {
		TeamSelectionController teamSelectionController = new TeamSelectionController(Constants.CLUBS[0], Constants.CLUBS[1]);
		List<Lineup> lineups = teamSelectionController.getSquads();

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

		scoreLineController = new ScoreLineController(Constants.CLUBS[0],Constants.CLUBS[1], lineups, setPieces);
	}

	@Test
	void getScoreLineTest() {
		HashMap<ClubModel, List<Goal>> goals = scoreLineController.getScoreLine();
		System.out.println(goals.size());
		assertEquals(2, goals.size());
	}
}