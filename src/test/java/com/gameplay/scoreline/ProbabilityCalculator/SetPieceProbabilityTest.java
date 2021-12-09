package com.gameplay.scoreline.ProbabilityCalculator;

import com.utils.Constants;
import com.models.ClubModel;
import com.models.SetPieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

		setPieceProbability = new SetPieceProbability(Constants.CLUBS[0], Constants.CLUBS[1], homeSetPieces,awaySetPieces);
	}

	@Test
	void getProbability() {
		HashMap<ClubModel, Double> probability = setPieceProbability.getProbability();
		assertEquals(2,probability.size());
	}
}