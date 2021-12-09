package com.utils;

import com.database_operations.DatabaseImport;
import com.models.*;

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
    public static final String CONNECTION_DEV_URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_25_DEVINT";
    public static final String CONNECTION_USERNAME = "CSCI5308_25_DEVINT_USER";
    public static final String CONNECTION_PASSWORD = "uhahMae3oonguNei";
    public static final String LOGGER_FILE_PATH = "src/main/java/com/log.log";
    public static PlayerModel[] PLAYERS = DatabaseImport.getInstance().getPlayers();
    public static ClubModel[] CLUBS = DatabaseImport.getInstance().getClubs();
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

	public static final int TOTAL_SEASONS_PLAYED_IN_SIMULATION = 10;

	public static class DatabaseConstants {

		// player status constants
		public static final String FETCH_ALL_PLAYER_IN_TEAM_QUERY = "SELECT * FROM player_status p WHERE p.club_id = ?";
		public static final String FETCH_PLAYER_BY_ID_QUERY = "SELECT * FROM player_status WHERE player_id = ?";
		public static final String DELETE_ALL_PLAYERS_QUERY = "DELETE FROM player_status";
		public static final String COPY_PLAYERS_DATA_QUERY = "INSERT INTO player_status (SELECT * FROM players)";

		// user player constants
		public static final String ADD_PLAYER_QUERY = "INSERT INTO user_players VALUES (?)";

		// user team constants
		public static final String INSERT_USER_TEAM_QUERY = "INSERT INTO user_team (`TEAM_ID`) VALUES (?)";
		public static final String UPDATE_CUSTOMIZED_PLAYING_XI_QUERY = "UPDATE user_team SET CUSTOMIZED_PLAYER = ?";
		public static final String UPDATE_SEASON_PLAYED_QUERY = "UPDATE user_team SET season_played = ?";
		public static final String FETCH_USER_TEAM_QUERY = "SELECT * FROM user_team";
		public static final String DELETE_ALL_USER_TEAM_QUERY = "DELETE FROM user_team";
	}

	public static class UserInput {

		public static final String PRINT_TEAM_ID_SELECTION_MSG = "Select team id of any one of the above listed teams.";
		public static final String PRINT_AVAILABLE_TEAM_MSG = "[AVAILABLE TEAMS]:";
		public static final String PRINT_PLAYER_SELECTION_OPTIONS = "Please select 11 players.\n"
				+ PlayerSelectionOptions.AVAILABLE_PLAYERS.getMessage() + "\n"
				+ PlayerSelectionOptions.SELECTED_PLAYERS.getMessage() + "\n" + PlayerSelectionOptions.ADD_PLAYER.getMessage()
				+ "\n" + PlayerSelectionOptions.REMOVE_PLAYER.getMessage() + "\n" + PlayerSelectionOptions.DONE.getMessage();
		public static final String PRINT_CUSTOMIZATION_OPTIONS = "Do you want to select playing 11?\n"
				+ BooleanOptions.YES.getMessage() + "\n" + BooleanOptions.NO.getMessage();
		public static final String AVAILABLE_PLAYERS = "[AVAILABLE PLAYERS]: ";
		public static final String SELECTED_PLAYERS = "[SELECTED PLAYERS]: ";
		public static final String OPTION_TO_RESUME_OR_START_OVER = "Do you want to resume the previous game or start a new Game?\n"
				+ StartOrResumeOptions.START_NEW_GAME.getMessage() + "\n"
				+ StartOrResumeOptions.RESUME_PREVIOUS_GAME.getMessage();
		public static final String INPUT_PLAYER_ID_MSG = "Please enter player id.";

	}

	public static final PlayerAttributes[] FREE_KICK_SKILLS = {
			PlayerAttributes.ATTACKING_CROSSING, PlayerAttributes.SKILL_CURVE, PlayerAttributes.SKILL_FK_ACCURACY,
			PlayerAttributes.POWER_SHOT_POWER, PlayerAttributes.POWER_LONG_SHOTS,
	};

	public static final PlayerAttributes[] CORNER_SKILLS = {
			PlayerAttributes.ATTACKING_HEADING_ACCURACY, PlayerAttributes.ATTACKING_VOLLEYS,
			PlayerAttributes.POWER_JUMPING, PlayerAttributes.MENTALITY_POSITIONING,
	};

	public static final PlayerAttributes[] OWN_GOAL_SKILLS = {
			PlayerAttributes.DEFENDING_MARKING_AWARENESS, PlayerAttributes.DEFENDING_SLIDING_TACKLE, PlayerAttributes.MOVEMENT_BALANCE,
	};

	public static final PlayerPositions[] ATTACKING_POSITIONS = {
			PlayerPositions.ST, PlayerPositions.LW, PlayerPositions.RW, PlayerPositions.CF, PlayerPositions.LS,
			PlayerPositions.RS, PlayerPositions.CAM, PlayerPositions.CM, PlayerPositions.LCM, PlayerPositions.RCM,
			PlayerPositions.LM, PlayerPositions.RM
	};

	public static final PlayerPositions[] DEFENDING_POSITIONS = {
			PlayerPositions.CB, PlayerPositions.RCB, PlayerPositions.LCB, PlayerPositions.LB, PlayerPositions.RB,
			PlayerPositions.LWB, PlayerPositions.RWB, PlayerPositions.LDM, PlayerPositions.RDM, PlayerPositions.CDM,
	};

	public static final int SEASON_COUNT = 10;
}
