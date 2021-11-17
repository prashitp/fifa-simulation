//Author - Prashit Patel
package com.models;

public class ClubModel {
	private int clubId;
	private String  clubName;
	int overall;
	int attack;
	int midfield;
	int defence;
	long transferBudget;

	public ClubModel(int clubId, String clubName, int overall, int attack, int midfield, int defence, long transferBudget) {
		this.clubId = clubId;
		this.clubName = clubName;
		this.overall = overall;
		this.attack = attack;
		this.midfield = midfield;
		this.defence = defence;
		this.transferBudget = transferBudget;
	}

	public int getClubId() {
		return clubId;
	}

	public String getClubName() {
		return clubName;
	}
}
