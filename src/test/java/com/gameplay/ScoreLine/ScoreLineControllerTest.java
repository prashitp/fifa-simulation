package com.gameplay.ScoreLine;

import com.Constants;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.models.ClubModel;
import com.models.gameplay.TeamSelection.Lineup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class ScoreLineControllerTest {
	static ScoreLineController scoreLineController;

	@BeforeAll
	public static void init() {
		ClubModel homeClub = Constants.CLUBS[0];
		ClubModel awayClub = Constants.CLUBS[1];

		TeamSelectionController teamSelectionController = new TeamSelectionController("Arsenal", "Aston Villa");
		List<Lineup> lineups = teamSelectionController.getSquads();

		HashMap<String, Integer> homeSetPieces = new HashMap<>();
		homeSetPieces.put("FREEKICK",13);
		homeSetPieces.put("CORNER",9);
		homeSetPieces.put("PENALTY",1);

		HashMap<String, Integer> awaySetPieces = new HashMap<>();
		awaySetPieces.put("FREEKICK",4);
		awaySetPieces.put("CORNER",5);
		awaySetPieces.put("PENALTY",1);

		List<HashMap<String, Integer>> setPieces= new ArrayList<>();
		setPieces.add(homeSetPieces);
		setPieces.add(awaySetPieces);

		scoreLineController = new ScoreLineController("Arsenal", "Aston Villa", lineups, setPieces);
	}

	@Test
	void getProbabilityTest() {
		HashMap<ClubModel, Integer> goals = scoreLineController.getScoreLine();
		assertEquals(2, goals.size());
	}
}