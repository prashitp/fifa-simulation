package com.models;

import java.util.HashMap;
/**
 * @author prashitpatel
 */

public class PlayerModel {
	private int playerId;
	private String playerName;
	private PlayerPositions[] positions;
	public int potential;
	public int overall;
	public int age;
	private int height;
	private int weight;
	public String club;
	public long value;
	private PlayerPositions clubPosition;
	public int clubJerseyNo;
	public int clubContractUntilYear;
	private String nationality;
	private String preferredFoot;
	private int skillMoves;
	private PlayerWorkRate workRate;
	private String[] playerTraits;
	public HashMap<PlayerAttributes, Integer> skills;

	public PlayerModel(int playerId, String playerName, PlayerPositions[] positions, int overall, int potential, long value, int age, int height, int weight, String club,
				PlayerPositions clubPosition, int clubJerseyNo, int clubContractUntilYear, String nationality, String preferredFoot, int skillMoves, PlayerWorkRate workRate,
					   String[] playerTraits, HashMap<PlayerAttributes,Integer> skills) {
		this.playerId =playerId;
		this.playerName = playerName;
		this.positions = positions;
		this.potential = potential;
		this.overall = overall;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.club = club;
		this.value = value;
		this.clubPosition = clubPosition;
		this.clubJerseyNo = clubJerseyNo;
		this.clubContractUntilYear = clubContractUntilYear;
		this.nationality = nationality;
		this.preferredFoot = preferredFoot;
		this.skillMoves = skillMoves;
		this.workRate = workRate;
		this.playerTraits = playerTraits;
		this.skills = skills;
	}
	public int getPlayerId() {return playerId;}

	public String getPlayerName() {
		return playerName;
	}

	public PlayerPositions[] getPositions() {
		return positions;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public PlayerPositions getClubPosition() {
		return clubPosition;
	}

	public String getNationality() {
		return nationality;
	}

	public String getPreferredFoot() {
		return preferredFoot;
	}

	public int getSkillMoves() {
		return skillMoves;
	}

	public PlayerWorkRate getWorkRate() {
		return workRate;
	}

	public String[] getPlayerTraits() {
		return playerTraits;
	}
}
