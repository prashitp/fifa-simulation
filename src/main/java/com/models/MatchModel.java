package com.models;

/**
 * @author Jay Patel
 */
public class MatchModel {

	private String homeTeamId;
	
	private String awayTeamId;

	public MatchModel() {
		super();
	}

	public MatchModel(String homeTeamId, String awayTeamId) {
		super();
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
	}

	public String getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(String homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public String getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(String awayTeamId) {
		this.awayTeamId = awayTeamId;
	}
}
