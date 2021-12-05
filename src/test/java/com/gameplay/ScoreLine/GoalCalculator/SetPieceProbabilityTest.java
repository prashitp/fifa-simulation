package com.gameplay.ScoreLine.GoalCalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class SetPieceProbabilityTest {
	static SetPieceProbability setPieceProbability;

	@BeforeAll
	public static void init() {
		HashMap<String, Integer> homeSetPieces = new HashMap<>();
		homeSetPieces.put("FREEKICK",13);
		homeSetPieces.put("CORNER",9);
		homeSetPieces.put("PENALTY",0);

		HashMap<String, Integer> awaySetPieces = new HashMap<>();
		awaySetPieces.put("FREEKICK",4);
		awaySetPieces.put("CORNER",5);
		awaySetPieces.put("PENALTY",0);
		setPieceProbability = new SetPieceProbability(homeSetPieces,awaySetPieces);
	}

	@Test
	void getProbability() {
		List<Double> probability = setPieceProbability.getProbability();
		assertEquals(2,probability.size());
	}
}