//Author - Prashit Patel
package com.models;

import java.util.ArrayList;

public class PlayerModel {

	private String playerName;
	private PlayerPositions[] positions;
	int potential;
	int overall;
	int age;
	private int height;
	private int weight;
	String club;
	long value;
	private PlayerPositions clubPosition;
	int clubJerseyNo;
	int clubContractUntilYear;
	private String nationality;
	private String preferredFoot;
	private int skillMoves;
	private PlayerWorkRate workRate;
	private String[] playerTraits;
	int pace;
	int shooting;
	int passing;
	int dribbling;
	int defending;
	int physic;
	int attackingCrossing;
	int attackingFinishing;
	int attackingHeadingAccuracy;
	int attackingShortPassing;
	int attackingVolleys;
	int skillDribbling;
	int skillCurve;
	int skillFkAccuracy;
	int skillLongPassing;
	int skillBallControl;
	int movementAcceleration;
	int movementSprintSpeed;
	int movementReactions;
	int movementAgility;
	int movementBalance;
	int powerShotPower;
	int powerJumping;
	int powerStamina;
	int powerStrength;
	int powerLongShots;
	int mentalityAggression;
	int mentalityInterceptions;
	int mentalityPositioning;
	int mentalityVision;
	int mentalityPenalties;
	int mentalityComposure;
	int defendingMarkingAwarness;
	int defendingStandingTackle;
	int defendingSlidingTackle;
	int goalkeepingDiving;
	int goalkeepingHandling;
	int goalkeepingKicking;
	int goalkeepingPositioning;
	int goalkeepingReflexes;
	int goalkeepingSpeed;

	public PlayerModel(String playerName, PlayerPositions[] positions, int overall, int potential, long value, int age, int height, int weight, String club,
				PlayerPositions clubPosition, int clubJerseyNo, int clubContractUntilYear, String nationality, String preferredFoot, int skillMoves, PlayerWorkRate workRate,
					   String[] playerTraits, int pace, int shooting, int passing, int dribbling, int defending, int physic, int attackingCrossing, int attackingFinishing,
				int attackingHeadingAccuracy, int attackingShortPassing, int attackingVolleys, int skillDribbling, int skillCurve, int skillFkAccuracy, int skillLongPassing,
				int skillBallControl, int movementAcceleration, int movementSprintSpeed, int movementAgility, int movementReactions, int movementBalance, int powerShotPower,
				int powerJumping, int powerStamina, int powerStrength, int powerLongShots, int mentalityAggression, int mentalityInterceptions, int mentalityPositioning,
				int mentalityVision, int mentalityPenalties, int mentalityComposure, int defendingMarkingAwarness, int defendingStandingTackle, int defendingSlidingTackle,
				int goalkeepingDiving, int goalkeepingHandling, int goalkeepingKicking, int goalkeepingPositioning, int goalkeepingReflexes, int goalkeepingSpeed) {

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
		this.pace = pace;
		this.shooting = shooting;
		this.passing = passing;
		this.dribbling = dribbling;
		this.defending = defending;
		this.physic = physic;
		this.attackingCrossing = attackingCrossing;
		this.attackingFinishing = attackingFinishing;
		this.attackingHeadingAccuracy= attackingHeadingAccuracy;
		this.attackingShortPassing = attackingShortPassing;
		this.attackingVolleys = attackingVolleys;
		this.skillDribbling = skillDribbling;
		this.skillCurve = skillCurve;
		this.skillFkAccuracy = skillFkAccuracy;
		this.skillLongPassing = skillLongPassing;
		this.skillBallControl = skillBallControl;
		this.movementAcceleration = movementAcceleration;
		this.movementSprintSpeed = movementSprintSpeed;
		this.movementReactions = movementReactions;
		this.movementAgility = movementAgility;
		this.movementBalance = movementBalance;
		this.powerShotPower = powerShotPower;
		this.powerJumping = powerJumping;
		this.powerStamina = powerStamina;
		this.powerStrength = powerStrength;
		this.powerLongShots = powerLongShots;
		this.mentalityAggression = mentalityAggression;
		this.mentalityInterceptions = mentalityInterceptions;
		this.mentalityPositioning = mentalityPositioning;
		this.mentalityVision = mentalityVision;
		this.mentalityPenalties = mentalityPenalties;
		this.mentalityComposure = mentalityComposure;
		this.defendingMarkingAwarness = defendingMarkingAwarness;
		this.defendingStandingTackle = defendingStandingTackle;
		this.defendingSlidingTackle = defendingSlidingTackle;
		this.goalkeepingDiving = goalkeepingDiving;
		this.goalkeepingHandling = goalkeepingHandling;
		this.goalkeepingKicking = goalkeepingKicking;
		this.goalkeepingPositioning = goalkeepingPositioning;
		this.goalkeepingReflexes = goalkeepingReflexes;
		this.goalkeepingSpeed = goalkeepingSpeed;
	}

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
