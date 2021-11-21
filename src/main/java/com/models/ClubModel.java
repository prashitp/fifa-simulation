package com.models;
import java.util.HashMap;

/**
 * @author prashitpatel
 */
public class ClubModel {
	private int clubId;
	private String  clubName;
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
}
