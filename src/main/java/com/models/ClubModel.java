package com.models;

import java.util.HashMap;
import com.Constants;
import java.util.Arrays;
/**
 * @author prashitpatel
 */
public class ClubModel {
	private final int clubId;
	private final String  clubName;
	public HashMap<ClubAttributes,Integer> attributes;
	long transferBudget;
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
