package com.gameplay.scoreline.ProbabilityCalculator;

import com.utils.Constants;
import com.models.ClubModel;
import com.models.SetPieceType;

import java.util.HashMap;
/**
 * @author prashitpatel
 */
public class SetPieceProbability extends ProbabilityCriteria {
	HashMap<SetPieceType, Integer> homeClubSetPieces;
	HashMap<SetPieceType, Integer> awayClubSetPieces;

	public SetPieceProbability(ClubModel homeClub, ClubModel awayClub, HashMap<SetPieceType, Integer> homeClubSetPieces, HashMap<SetPieceType, Integer> awayClubSetPieces) {
		super(homeClub, awayClub);
		this.homeClubSetPieces = homeClubSetPieces;
		this.awayClubSetPieces = awayClubSetPieces;
	}

	public HashMap<ClubModel, Double> getProbability() {
		int homeCorners = homeClubSetPieces.get(SetPieceType.CORNER_KICK);
		int homeFreeKicks = homeClubSetPieces.get(SetPieceType.FREE_KICK);
		int awayCorners = awayClubSetPieces.get(SetPieceType.CORNER_KICK);
		int awayFreeKicks = awayClubSetPieces.get(SetPieceType.FREE_KICK);

		double homeProbability = Constants.STARTING_PROBABILITY + ((double) homeCorners/100) + ((double) homeFreeKicks/200);
		double awayProbability = Constants.STARTING_PROBABILITY + ((double) awayCorners/100) + ((double) awayFreeKicks/200);

		HashMap<ClubModel, Double> setPieceProbability = new HashMap<>();
		setPieceProbability.put(homeClub, homeProbability);
		setPieceProbability.put(awayClub, awayProbability);

		return setPieceProbability;
	}
}
