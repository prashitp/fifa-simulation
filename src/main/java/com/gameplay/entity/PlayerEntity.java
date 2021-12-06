package com.gameplay.entity;

/**
 * @author Jay Patel
 */
public class PlayerEntity {

	@Column(name = "player_id")
	private String playerId;

	@Column(name = "player_name")
	private String playerName;

	@Column(name = "player_positions")
	private String playerPositions;

	@Column(name = "potential")
	private Integer potential;

	@Column(name = "overall")
	private Integer overall;

	@Column(name = "age")
	private Integer age;

	@Column(name = "height_cm")
	private Integer height;

	@Column(name = "weight_kg")
	private Integer weight;

	@Column(name = "club_id")
	private String clubId;

	@Column(name = "value_eur")
	private Integer value;

	@Column(name = "club_position")
	private String clubPosition;

	@Column(name = "club_jersey_number")
	private Integer clubJerseyNo;

	@Column(name = "club_contract_valid_until")
	private Integer clubContractValidUntilYear;

	@Column(name = "nationality_id")
	private String nationalityId;

	@Column(name = "preferred_foot")
	private String preferredFoot;

	@Column(name = "skill_moves")
	private Integer skillMoves;

	@Column(name = "work_rate")
	private String workRate;

	@Column(name = "player_traits")
	private String playerTraits;

	@Column(name = "pace")
	private Integer pace;

	@Column(name = "shooting")
	private Integer shooting;

	@Column(name = "passing")
	private Integer passing;

	@Column(name = "dribbling")
	private Integer dribbling;

	@Column(name = "defending")
	private Integer defending;

	@Column(name = "physic")
	private Integer physic;

	@Column(name = "attacking_crossing")
	private Integer attackingCrossing;

	@Column(name = "attacking_finishing")
	private Integer attackingFinishing;

	@Column(name = "attacking_heading_accuracy")
	private Integer attackingHeadingAccuracy;

	@Column(name = "attacking_short_passing")
	private Integer attackingShortPassing;

	@Column(name = "attacking_volleys")
	private Integer attackingVolleys;

	@Column(name = "skill_dribbling")
	private Integer skillDribbling;

	@Column(name = "skill_curve")
	private Integer skillCurve;

	@Column(name = "skill_fk_accuracy")
	private Integer skillFkAccuracy;

	@Column(name = "skill_long_passing")
	private Integer skillLongPassing;

	@Column(name = "skill_ball_control")
	private Integer skillBallControl;

	@Column(name = "movement_acceleration")
	private Integer movementAcceleration;

	@Column(name = "movement_sprint_speed")
	private Integer movementSprintSpeed;

	@Column(name = "movement_reactions")
	private Integer movementReactions;

	@Column(name = "movement_agility")
	private Integer movementAgility;

	@Column(name = "movement_balance")
	private Integer movementBalance;

	@Column(name = "power_shot_power")
	private Integer powerShotPower;

	@Column(name = "power_jumping")
	private Integer powerJumping;

	@Column(name = "power_stamina")
	private Integer powerStamina;

	@Column(name = "power_strength")
	private Integer powerStrength;

	@Column(name = "power_long_shots")
	private Integer powerLongShots;

	@Column(name = "mentality_aggression")
	private Integer mentalityAggression;

	@Column(name = "mentality_interceptions")
	private Integer mentalityInterceptions;

	@Column(name = "mentality_positioning")
	private Integer mentalityPositioning;

	@Column(name = "mentality_vision")
	private Integer mentalityVision;

	@Column(name = "mentality_penalties")
	private Integer mentalityPenalties;

	@Column(name = "mentality_composure")
	private Integer mentalityComposure;

	@Column(name = "defending_marking_awareness")
	private Integer defendingMarkingAwareness;

	@Column(name = "defending_standing_tackle")
	private Integer defendingStandingTackle;

	@Column(name = "defending_sliding_tackle")
	private Integer defendingSlidingTackle;

	@Column(name = "goalkeeping_diving")
	private Integer goalKeepingDiving;

	@Column(name = "goalkeeping_handling")
	private Integer goalKeepingHandling;

	@Column(name = "goalkeeping_kicking")
	private Integer goalKeepingKicking;

	@Column(name = "goalkeeping_positioning")
	private Integer goalKeepingPositioning;

	@Column(name = "goalkeeping_reflexes")
	private Integer goalKeepingReflexes;

	@Column(name = "goalkeeping_speed")
	private Integer goalKeepingSpeed;

	public PlayerEntity() {
		super();
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerPositions() {
		return playerPositions;
	}

	public void setPlayerPositions(String playerPositions) {
		this.playerPositions = playerPositions;
	}

	public Integer getPotential() {
		return potential;
	}

	public void setPotential(Integer potential) {
		this.potential = potential;
	}

	public Integer getOverall() {
		return overall;
	}

	public void setOverall(Integer overall) {
		this.overall = overall;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getClubPosition() {
		return clubPosition;
	}

	public void setClubPosition(String clubPosition) {
		this.clubPosition = clubPosition;
	}

	public Integer getClubJerseyNo() {
		return clubJerseyNo;
	}

	public void setClubJerseyNo(Integer clubJerseyNo) {
		this.clubJerseyNo = clubJerseyNo;
	}

	public Integer getClubContractValidUntilYear() {
		return clubContractValidUntilYear;
	}

	public void setClubContractValidUntilYear(Integer clubContractValidUntilYear) {
		this.clubContractValidUntilYear = clubContractValidUntilYear;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getPreferredFoot() {
		return preferredFoot;
	}

	public void setPreferredFoot(String preferredFoot) {
		this.preferredFoot = preferredFoot;
	}

	public Integer getSkillMoves() {
		return skillMoves;
	}

	public void setSkillMoves(Integer skillMoves) {
		this.skillMoves = skillMoves;
	}

	public String getWorkRate() {
		return workRate;
	}

	public void setWorkRate(String workRate) {
		this.workRate = workRate;
	}

	public String getPlayerTraits() {
		return playerTraits;
	}

	public void setPlayerTraits(String playerTraits) {
		this.playerTraits = playerTraits;
	}

	public Integer getPace() {
		return pace;
	}

	public void setPace(Integer pace) {
		this.pace = pace;
	}

	public Integer getShooting() {
		return shooting;
	}

	public void setShooting(Integer shooting) {
		this.shooting = shooting;
	}

	public Integer getPassing() {
		return passing;
	}

	public void setPassing(Integer passing) {
		this.passing = passing;
	}

	public Integer getDribbling() {
		return dribbling;
	}

	public void setDribbling(Integer dribbling) {
		this.dribbling = dribbling;
	}

	public Integer getDefending() {
		return defending;
	}

	public void setDefending(Integer defending) {
		this.defending = defending;
	}

	public Integer getPhysic() {
		return physic;
	}

	public void setPhysic(Integer physic) {
		this.physic = physic;
	}

	public Integer getAttackingCrossing() {
		return attackingCrossing;
	}

	public void setAttackingCrossing(Integer attackingCrossing) {
		this.attackingCrossing = attackingCrossing;
	}

	public Integer getAttackingFinishing() {
		return attackingFinishing;
	}

	public void setAttackingFinishing(Integer attackingFinishing) {
		this.attackingFinishing = attackingFinishing;
	}

	public Integer getAttackingHeadingAccuracy() {
		return attackingHeadingAccuracy;
	}

	public void setAttackingHeadingAccuracy(Integer attackingHeadingAccuracy) {
		this.attackingHeadingAccuracy = attackingHeadingAccuracy;
	}

	public Integer getAttackingShortPassing() {
		return attackingShortPassing;
	}

	public void setAttackingShortPassing(Integer attackingShortPassing) {
		this.attackingShortPassing = attackingShortPassing;
	}

	public Integer getAttackingVolleys() {
		return attackingVolleys;
	}

	public void setAttackingVolleys(Integer attackingVolleys) {
		this.attackingVolleys = attackingVolleys;
	}

	public Integer getSkillDribbling() {
		return skillDribbling;
	}

	public void setSkillDribbling(Integer skillDribbling) {
		this.skillDribbling = skillDribbling;
	}

	public Integer getSkillCurve() {
		return skillCurve;
	}

	public void setSkillCurve(Integer skillCurve) {
		this.skillCurve = skillCurve;
	}

	public Integer getSkillFkAccuracy() {
		return skillFkAccuracy;
	}

	public void setSkillFkAccuracy(Integer skillFkAccuracy) {
		this.skillFkAccuracy = skillFkAccuracy;
	}

	public Integer getSkillLongPassing() {
		return skillLongPassing;
	}

	public void setSkillLongPassing(Integer skillLongPassing) {
		this.skillLongPassing = skillLongPassing;
	}

	public Integer getSkillBallControl() {
		return skillBallControl;
	}

	public void setSkillBallControl(Integer skillBallControl) {
		this.skillBallControl = skillBallControl;
	}

	public Integer getMovementAcceleration() {
		return movementAcceleration;
	}

	public void setMovementAcceleration(Integer movementAcceleration) {
		this.movementAcceleration = movementAcceleration;
	}

	public Integer getMovementSprintSpeed() {
		return movementSprintSpeed;
	}

	public void setMovementSprintSpeed(Integer movementSprintSpeed) {
		this.movementSprintSpeed = movementSprintSpeed;
	}

	public Integer getMovementReactions() {
		return movementReactions;
	}

	public void setMovementReactions(Integer movementReactions) {
		this.movementReactions = movementReactions;
	}

	public Integer getMovementAgility() {
		return movementAgility;
	}

	public void setMovementAgility(Integer movementAgility) {
		this.movementAgility = movementAgility;
	}

	public Integer getMovementBalance() {
		return movementBalance;
	}

	public void setMovementBalance(Integer movementBalance) {
		this.movementBalance = movementBalance;
	}

	public Integer getPowerShotPower() {
		return powerShotPower;
	}

	public void setPowerShotPower(Integer powerShotPower) {
		this.powerShotPower = powerShotPower;
	}

	public Integer getPowerJumping() {
		return powerJumping;
	}

	public void setPowerJumping(Integer powerJumping) {
		this.powerJumping = powerJumping;
	}

	public Integer getPowerStamina() {
		return powerStamina;
	}

	public void setPowerStamina(Integer powerStamina) {
		this.powerStamina = powerStamina;
	}

	public Integer getPowerStrength() {
		return powerStrength;
	}

	public void setPowerStrength(Integer powerStrength) {
		this.powerStrength = powerStrength;
	}

	public Integer getPowerLongShots() {
		return powerLongShots;
	}

	public void setPowerLongShots(Integer powerLongShots) {
		this.powerLongShots = powerLongShots;
	}

	public Integer getMentalityAggression() {
		return mentalityAggression;
	}

	public void setMentalityAggression(Integer mentalityAggression) {
		this.mentalityAggression = mentalityAggression;
	}

	public Integer getMentalityInterceptions() {
		return mentalityInterceptions;
	}

	public void setMentalityInterceptions(Integer mentalityInterceptions) {
		this.mentalityInterceptions = mentalityInterceptions;
	}

	public Integer getMentalityPositioning() {
		return mentalityPositioning;
	}

	public void setMentalityPositioning(Integer mentalityPositioning) {
		this.mentalityPositioning = mentalityPositioning;
	}

	public Integer getMentalityVision() {
		return mentalityVision;
	}

	public void setMentalityVision(Integer mentalityVision) {
		this.mentalityVision = mentalityVision;
	}

	public Integer getMentalityPenalties() {
		return mentalityPenalties;
	}

	public void setMentalityPenalties(Integer mentalityPenalties) {
		this.mentalityPenalties = mentalityPenalties;
	}

	public Integer getMentalityComposure() {
		return mentalityComposure;
	}

	public void setMentalityComposure(Integer mentalityComposure) {
		this.mentalityComposure = mentalityComposure;
	}

	public Integer getDefendingMarkingAwareness() {
		return defendingMarkingAwareness;
	}

	public void setDefendingMarkingAwareness(Integer defendingMarkingAwareness) {
		this.defendingMarkingAwareness = defendingMarkingAwareness;
	}

	public Integer getDefendingStandingTackle() {
		return defendingStandingTackle;
	}

	public void setDefendingStandingTackle(Integer defendingStandingTackle) {
		this.defendingStandingTackle = defendingStandingTackle;
	}

	public Integer getDefendingSlidingTackle() {
		return defendingSlidingTackle;
	}

	public void setDefendingSlidingTackle(Integer defendingSlidingTackle) {
		this.defendingSlidingTackle = defendingSlidingTackle;
	}

	public Integer getGoalKeepingDiving() {
		return goalKeepingDiving;
	}

	public void setGoalKeepingDiving(Integer goalKeepingDiving) {
		this.goalKeepingDiving = goalKeepingDiving;
	}

	public Integer getGoalKeepingHandling() {
		return goalKeepingHandling;
	}

	public void setGoalKeepingHandling(Integer goalKeepingHandling) {
		this.goalKeepingHandling = goalKeepingHandling;
	}

	public Integer getGoalKeepingKicking() {
		return goalKeepingKicking;
	}

	public void setGoalKeepingKicking(Integer goalKeepingKicking) {
		this.goalKeepingKicking = goalKeepingKicking;
	}

	public Integer getGoalKeepingPositioning() {
		return goalKeepingPositioning;
	}

	public void setGoalKeepingPositioning(Integer goalKeepingPositioning) {
		this.goalKeepingPositioning = goalKeepingPositioning;
	}

	public Integer getGoalKeepingReflexes() {
		return goalKeepingReflexes;
	}

	public void setGoalKeepingReflexes(Integer goalKeepingReflexes) {
		this.goalKeepingReflexes = goalKeepingReflexes;
	}

	public Integer getGoalKeepingSpeed() {
		return goalKeepingSpeed;
	}

	public void setGoalKeepingSpeed(Integer goalKeepingSpeed) {
		this.goalKeepingSpeed = goalKeepingSpeed;
	}
}
