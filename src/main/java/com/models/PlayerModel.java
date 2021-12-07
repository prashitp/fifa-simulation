package com.models;

import java.util.HashMap;
/**
 * @author prashitpatel and vasugamdha
 */

public class PlayerModel {
	private final int playerId;
	private final String playerName;
	private final PlayerPositions[] positions;
	public int potential;
	public int overall;
	public int age;
	private final int height;
	private final int weight;
	public String club;
	public long value;
	private final PlayerPositions clubPosition;
	public int clubJerseyNo;
	public int clubContractUntilYear;
	private final String nationality;
	private String preferredFoot;
	private int skillMoves;
	private PlayerWorkRate workRate;
	private String[] playerTraits;
	public HashMap<PlayerAttributes, Integer> skills;
	public Boolean yellowCard;
	public int injuredForMatches;
	public Boolean availability;

	public PlayerModel(int playerId, String playerName, PlayerPositions[] positions, int overall, int potential,
					   long value, int age, int height, int weight, String club, PlayerPositions clubPosition,
					   int clubJerseyNo, int clubContractUntilYear, String nationality, String preferredFoot,
					   int skillMoves, PlayerWorkRate workRate, String[] playerTraits,
					   HashMap<PlayerAttributes,Integer> skills, Boolean yellowCard,
					   int injuredForMatches, Boolean availability) {
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
		this.yellowCard = yellowCard;
		this.injuredForMatches = injuredForMatches;
		this.availability = availability;
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

	public Boolean isAvailable(){
		return availability;
	}
}
