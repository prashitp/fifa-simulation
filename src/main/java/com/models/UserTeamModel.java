package com.models;

/**
 * @author Jay Patel
 */
public class UserTeamModel {

	private Integer id;
	private Integer teamId;
	private Boolean customizedPlayer;
	private Integer seasonPlayed;
	
	public UserTeamModel() {
		super();
	}

	public UserTeamModel(Integer id, Integer teamId, Boolean customizedPlayer, Integer seasonPlayed) {
		super();
		this.id = id;
		this.teamId = teamId;
		this.customizedPlayer = customizedPlayer;
		this.seasonPlayed = seasonPlayed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Boolean getCustomizedPlayer() {
		return customizedPlayer;
	}

	public void setCustomizedPlayer(Boolean customizedPlayer) {
		this.customizedPlayer = customizedPlayer;
	}

	public Integer getSeasonPlayed() {
		return seasonPlayed;
	}

	public void setSeasonPlayed(Integer seasonPlayed) {
		this.seasonPlayed = seasonPlayed;
	}
}
