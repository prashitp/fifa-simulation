package com;

import com.database_operations.DatabaseImport;
import com.models.*;

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
}
