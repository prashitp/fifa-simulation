package com.models;

import com.utils.Constants;

import java.util.Arrays;
import java.util.HashMap;
/**
 * @author prashitpatel
 */
public class ClubModel {
	private final int clubId;
	private final String  clubName;
	public HashMap<ClubAttributes,Integer> attributes;
	public long transferBudget;
	public int matchesPlayed = 0;
	public int matchesWin = 0;
	public int matchesDraw = 0;
	public int matchesLoss = 0;
	public int goals = 0;
	public int points = 0;

	public ClubModel(int clubId, String clubName, HashMap<ClubAttributes,Integer> attributes, long transferBudget) {
		this.clubId = clubId;
		this.clubName = clubName;
		this.attributes = attributes;
		this.transferBudget = transferBudget;
	}

	public int getClubId() {
		return clubId;
	}

	public String getClubName() {
		return clubName;
	}

	public PlayerModel[] getPlayers() {
		return Arrays.stream(Constants.PLAYERS).filter(player -> player.club.equals(clubName)).toArray(PlayerModel[]::new);
	}
}
