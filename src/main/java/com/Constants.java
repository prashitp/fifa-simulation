package com;

import com.database_operations.DatabaseImport;
import com.models.*;

/**
 * @authors: Mayank Sareen, Prashit Patel, and Vasu Gamdha
 */

import com.models.ClubModel;
import com.models.PlayerModel;
import com.models.PlayerPositions;
import com.models.PlayerWorkRate;


public class Constants {
	public static final int DEFENDERS_MIN = 3;
	public static final int DEFENDERS_MAX = 5;
	public static final int MIDFIELDERS_MIN = 3;
	public static final int MIDFIELDERS_MAX = 5;
	public static final int DEFENSIVE_MIDFIELDERS_MIN = 1;
	public static final int DEFENSIVE_MIDFIELDERS_MAX = 4;
	public static final int ATTACKING_MIDFIELDERS_MIN = 1;
	public static final int ATTACKING_MIDFIELDERS_MAX = 4;
	public static final int FORWARDS_MIN = 1;
	public static final int FORWARDS_MAX = 3;
	public static final int FORMATION_SUM = 10;
	public static final String JDBC_CLASS_INITIALIZATION = "com.mysql.cj.jdbc.Driver";
	public static final String CONNECTION_DEV_URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_25_DEVINT";
	public static final String CONNECTION_USERNAME = "CSCI5308_25_DEVINT_USER";
	public static final String CONNECTION_PASSWORD = "uhahMae3oonguNei";
	public static final String LOGGER_FILE_PATH = "src/main/java/log.log";
	public static final PlayerModel[] PLAYERS = DatabaseImport.getInstance().getPlayers();
	public static final ClubModel[] CLUBS = DatabaseImport.getInstance().getClubs();
	public static final PlayerPositions[] forwardPositions = {
		PlayerPositions.ST,PlayerPositions.LW,PlayerPositions.RW,PlayerPositions.CF,PlayerPositions.LS,PlayerPositions.RS
	};
	public static final PlayerPositions[] midfielderPositions = {
		PlayerPositions.CM,PlayerPositions.CAM,PlayerPositions.CDM,PlayerPositions.LM,PlayerPositions.RM,
			PlayerPositions.LWB,PlayerPositions.RWB,PlayerPositions.LCM,PlayerPositions.RCM,PlayerPositions.LDM,
			PlayerPositions.RDM
	};
	public static final PlayerPositions[] defenderPositions = {
		PlayerPositions.CB,PlayerPositions.RCB,PlayerPositions.LCB,PlayerPositions.LB,PlayerPositions.RB
	};
	public static final PlayerPositions[] goalkeeperPositions = {
			PlayerPositions.GK
	};
	public static final int MATCH_READY_STAMINA_FORWARDS = 50;
	public static final int MATCH_READY_STAMINA_MIDFIELDERS = 60;
	public static final int MATCH_READY_STAMINA_DEFENDERS = 40;
	public static final int MATCH_READY_STAMINA_GOALKEEPER = 10;

	public static final PlayerAttributes[] attackingSkills = {
			PlayerAttributes.AttackingCrossing, PlayerAttributes.AttackingFinishing, PlayerAttributes.MentalityVision,
			PlayerAttributes.AttackingVolleys, PlayerAttributes.AttackingShortPassing,  PlayerAttributes.MentalityPenalties,
			PlayerAttributes.AttackingHeadingAccuracy, PlayerAttributes.Pace, PlayerAttributes.Shooting,
			PlayerAttributes.MovementSprintSpeed, PlayerAttributes.PowerShotPower, PlayerAttributes.MovementReactions
	};

	public static final PlayerAttributes[] midfieldSkills = {
			PlayerAttributes.SkillBallControl, PlayerAttributes.SkillDribbling, PlayerAttributes.Passing,
			PlayerAttributes.SkillFkAccuracy, PlayerAttributes.SkillCurve, PlayerAttributes.Dribbling,
			PlayerAttributes.SkillLongPassing, PlayerAttributes.MovementAcceleration, PlayerAttributes.MovementAgility,
			PlayerAttributes.PowerStamina, PlayerAttributes.PowerLongShots, PlayerAttributes.MentalityVision
	};

	public static final PlayerAttributes[] defendingSkills = {
			PlayerAttributes.DefendingMarkingAwareness, PlayerAttributes.DefendingStandingTackle, PlayerAttributes.Defending,
			PlayerAttributes.DefendingSlidingTackle, PlayerAttributes.MovementBalance, PlayerAttributes.PowerJumping,
			PlayerAttributes.PowerStrength, PlayerAttributes.MentalityAggression, PlayerAttributes.MentalityComposure,
			PlayerAttributes.MentalityInterceptions, PlayerAttributes.MentalityPositioning, PlayerAttributes.Physic
	};

	public static final PlayerAttributes[] goalkeepingSkills = {
			PlayerAttributes.GoalkeepingDiving, PlayerAttributes.GoalkeepingHandling, PlayerAttributes.PowerJumping,
			PlayerAttributes.GoalkeepingSpeed, PlayerAttributes.GoalkeepingKicking, PlayerAttributes.MovementReactions,
			PlayerAttributes.GoalkeepingPositioning, PlayerAttributes.GoalkeepingReflexes,PlayerAttributes.MentalityVision,
			PlayerAttributes.MentalityPositioning, PlayerAttributes.SkillLongPassing, PlayerAttributes.MentalityComposure
	};
}
