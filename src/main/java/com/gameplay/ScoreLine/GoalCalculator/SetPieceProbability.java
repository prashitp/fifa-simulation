package com.gameplay.ScoreLine.GoalCalculator;

import com.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class SetPieceProbability implements IProbabilityCalculator{
	HashMap<String, Integer> homeClubSetPieces;
	HashMap<String, Integer> awayClubSetPieces;

	public SetPieceProbability(HashMap<String, Integer> homeClubSetPieces, HashMap<String, Integer> awayClubSetPieces) {
		this.homeClubSetPieces = homeClubSetPieces;
		this.awayClubSetPieces = awayClubSetPieces;
	}

	public List<Double> getProbability() {
		int homeCorners = homeClubSetPieces.get("CORNER");
		int homeFreeKicks = homeClubSetPieces.get("FREEKICK");
		int awayCorners = awayClubSetPieces.get("CORNER");
		int awayFreeKicks = awayClubSetPieces.get("FREEKICK");

		double homeProbability = Constants.STARTING_PROBABILITY + ((double) homeCorners/100) + ((double) homeFreeKicks/200);
		double awayProbability = Constants.STARTING_PROBABILITY + ((double) awayCorners/100) + ((double) awayFreeKicks/200);

		List<Double> setPieceProbability = new ArrayList<>();
		setPieceProbability.add(homeProbability);
		setPieceProbability.add(awayProbability);

		return setPieceProbability;
	}
}
