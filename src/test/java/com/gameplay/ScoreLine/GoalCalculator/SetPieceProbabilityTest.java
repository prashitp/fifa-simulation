package com.gameplay.ScoreLine.GoalCalculator;

import com.models.SetPieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
		HashMap<SetPieceType, Integer> homeSetPieces = new HashMap<>();
		homeSetPieces.put(SetPieceType.FREE_KICK,13);
		homeSetPieces.put(SetPieceType.CORNER_KICK,9);
		homeSetPieces.put(SetPieceType.PENALTY_KICK,1);

		HashMap<SetPieceType, Integer> awaySetPieces = new HashMap<>();
		awaySetPieces.put(SetPieceType.FREE_KICK,4);
		awaySetPieces.put(SetPieceType.CORNER_KICK,5);
		awaySetPieces.put(SetPieceType.PENALTY_KICK,1);

		setPieceProbability = new SetPieceProbability(homeSetPieces,awaySetPieces);
	}

	@Test
	void getProbability() {
		List<Double> probability = setPieceProbability.getProbability();
		assertEquals(2,probability.size());
	}
}