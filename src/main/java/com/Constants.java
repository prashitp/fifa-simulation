package com;

import com.database_operations.DatabaseImport;
import com.models.ClubModel;
import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayerPositions;

/**
 * @authors: Mayank Sareen, Prashit Patel, and Vasu Gamdha
 */


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
    public static final PlayerPositions[] FORWARD_POSITIONS = {
            PlayerPositions.ST, PlayerPositions.LW, PlayerPositions.RW, PlayerPositions.CF, PlayerPositions.LS,
			PlayerPositions.RS
    };
    public static final PlayerPositions[] MIDFIELDER_POSITIONS = {
            PlayerPositions.CM, PlayerPositions.CAM, PlayerPositions.CDM, PlayerPositions.LM, PlayerPositions.RM,
            PlayerPositions.LWB, PlayerPositions.RWB, PlayerPositions.LCM, PlayerPositions.RCM, PlayerPositions.LDM,
            PlayerPositions.RDM
    };
    public static final PlayerPositions[] DEFENDER_POSITIONS = {
            PlayerPositions.CB, PlayerPositions.RCB, PlayerPositions.LCB, PlayerPositions.LB, PlayerPositions.RB
    };
    public static final PlayerPositions[] GOALKEEPER_POSITIONS = {
            PlayerPositions.GK
    };
    public static final int MATCH_READY_STAMINA_FORWARDS = 50;
    public static final int MATCH_READY_STAMINA_MIDFIELDERS = 60;
    public static final int MATCH_READY_STAMINA_DEFENDERS = 40;
    public static final int MATCH_READY_STAMINA_GOALKEEPER = 10;

    public static final PlayerAttributes[] ATTACKING_SKILLS = {
            PlayerAttributes.ATTACKING_CROSSING, PlayerAttributes.ATTACKING_FINISHING, PlayerAttributes.MENTALITY_VISION,
            PlayerAttributes.ATTACKING_VOLLEYS, PlayerAttributes.ATTACKING_SHORT_PASSING, PlayerAttributes.MENTALITY_PENALTIES,
            PlayerAttributes.ATTACKING_HEADING_ACCURACY, PlayerAttributes.PACE, PlayerAttributes.SHOOTING,
            PlayerAttributes.MOVEMENT_SPRINT_SPEED, PlayerAttributes.POWER_SHOT_POWER, PlayerAttributes.MOVEMENT_REACTIONS
    };

    public static final PlayerAttributes[] MIDFIELD_SKILLS = {
            PlayerAttributes.SKILL_BALL_CONTROL, PlayerAttributes.SKILL_DRIBBLING, PlayerAttributes.PASSING,
            PlayerAttributes.SKILL_FK_ACCURACY, PlayerAttributes.SKILL_CURVE, PlayerAttributes.DRIBBLING,
            PlayerAttributes.SKILL_LONG_PASSING, PlayerAttributes.MOVEMENT_ACCELERATION, PlayerAttributes.MOVEMENT_AGILITY,
            PlayerAttributes.POWER_STAMINA, PlayerAttributes.POWER_LONG_SHOTS, PlayerAttributes.MENTALITY_VISION
    };

    public static final PlayerAttributes[] DEFENDING_SKILLS = {
            PlayerAttributes.DEFENDING_MARKING_AWARENESS, PlayerAttributes.DEFENDING_STANDING_TACKLE, PlayerAttributes.DEFENDING,
            PlayerAttributes.DEFENDING_SLIDING_TACKLE, PlayerAttributes.MOVEMENT_BALANCE, PlayerAttributes.POWER_JUMPING,
            PlayerAttributes.POWER_STRENGTH, PlayerAttributes.MENTALITY_AGGRESSION, PlayerAttributes.MENTALITY_COMPOSURE,
            PlayerAttributes.MENTALITY_INTERCEPTIONS, PlayerAttributes.MENTALITY_POSITIONING, PlayerAttributes.PHYSIC
    };

    public static final PlayerAttributes[] GOALKEEPING_SKILLS = {
            PlayerAttributes.GOALKEEPING_DIVING, PlayerAttributes.GOALKEEPING_HANDLING, PlayerAttributes.POWER_JUMPING,
            PlayerAttributes.GOALKEEPING_SPEED, PlayerAttributes.GOALKEEPING_KICKING, PlayerAttributes.MOVEMENT_REACTIONS,
            PlayerAttributes.GOALKEEPING_POSITIONING, PlayerAttributes.GOALKEEPING_REFLEXES, PlayerAttributes.MENTALITY_VISION,
            PlayerAttributes.MENTALITY_POSITIONING, PlayerAttributes.SKILL_LONG_PASSING, PlayerAttributes.MENTALITY_COMPOSURE
    };
    public static final double STARTING_PROBABILITY = 0.5;
}
