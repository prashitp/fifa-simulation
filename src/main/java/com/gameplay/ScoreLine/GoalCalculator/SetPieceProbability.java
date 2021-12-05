package com.gameplay.ScoreLine.GoalCalculator;

import com.Constants;
import com.gameplay.SetPiece.SetPiece;
import com.models.SetPieceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class SetPieceProbability implements IProbabilityCalculator{
	HashMap<SetPieceType, Integer> homeClubSetPieces;
	HashMap<SetPieceType, Integer> awayClubSetPieces;

	public SetPieceProbability(HashMap<SetPieceType, Integer> homeClubSetPieces, HashMap<SetPieceType, Integer> awayClubSetPieces) {
		this.homeClubSetPieces = homeClubSetPieces;
		this.awayClubSetPieces = awayClubSetPieces;
	}

	public List<Double> getProbability() {
		int homeCorners = homeClubSetPieces.get(SetPieceType.CORNER_KICK);
		int homeFreeKicks = homeClubSetPieces.get(SetPieceType.FREE_KICK);
		int awayCorners = awayClubSetPieces.get(SetPieceType.CORNER_KICK);
		int awayFreeKicks = awayClubSetPieces.get(SetPieceType.FREE_KICK);

		double homeProbability = Constants.STARTING_PROBABILITY + ((double) homeCorners/100) + ((double) homeFreeKicks/200);
		double awayProbability = Constants.STARTING_PROBABILITY + ((double) awayCorners/100) + ((double) awayFreeKicks/200);

		List<Double> setPieceProbability = new ArrayList<>();
		setPieceProbability.add(homeProbability);
		setPieceProbability.add(awayProbability);

		return setPieceProbability;
	}
}
