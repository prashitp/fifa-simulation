package com.gameplay.UserInput.validation;

import java.util.Arrays;
import java.util.List;

import com.CommonFunctions;
import com.Constants;
import com.gameplay.entity.PlayerEntity;
import com.models.PlayerPositions;
import com.models.TeamValidationParameters;
import com.models.TeamValidationParameters.Builder;

/**
 * @author Jay Patel
 */
public class TeamValidation {

	private static final Integer TEAM_SIZE = 11;

	private static Boolean[][][][][] isVisited;

	private static void init() {
		isVisited = new Boolean[TEAM_SIZE + 1][TEAM_SIZE + 1][TEAM_SIZE + 1][TEAM_SIZE + 1][TEAM_SIZE + 1];
		for (Boolean[][][][] a : isVisited) {
			Arrays.fill(a, new Boolean[TEAM_SIZE + 1][TEAM_SIZE + 1][TEAM_SIZE + 1]);
			for (Boolean[][][] b : a) {
				Arrays.fill(b, new Boolean[TEAM_SIZE + 1][TEAM_SIZE + 1]);
				for (Boolean[][] c : b) {
					Arrays.fill(c, new Boolean[TEAM_SIZE + 1]);
					for (Boolean[] d : c) {
						Arrays.fill(d, Boolean.FALSE);
					}
				}
			}
		}
	}

	public static Boolean isTeamValid(List<PlayerEntity> players) {
		if (players == null || players.size() != TEAM_SIZE) {
			return Boolean.FALSE;
		}
		init();
		TeamValidationParameters parameters = new Builder().setCurPlayerIndex(0).setTeamSize(0).setNumberOfDefenders(0)
				.setNumberOfMidFielders(0).setNumberOfForwards(0).setNumberOfGoalKeep(0).getTeamValidationParameters();
		return checkTeamIsValid(players, parameters);
	}

	private static Boolean checkTeamIsValid(List<PlayerEntity> players, TeamValidationParameters parameters) {
		if (parameters.getTeamSize() == TEAM_SIZE) {
			return isValidSelection(parameters);
		}
		if (players == null || isVisitedState(parameters) || players.size() <= parameters.getCurPlayerIndex()) {
			return Boolean.FALSE;
		}
		for (PlayerPositions position : CommonFunctions
				.fetchPlayerPositions(players.get(parameters.getCurPlayerIndex()))) {
			Boolean isDefender = Arrays.asList(Constants.DEFENDER_POSITIONS).contains(position);
			Boolean isMidFielder = Arrays.asList(Constants.MIDFIELDER_POSITIONS).contains(position);
			Boolean isForward = Arrays.asList(Constants.FORWARD_POSITIONS).contains(position);
			Boolean isGoalKeeper = Arrays.asList(Constants.GOALKEEPER_POSITIONS).contains(position);

			TeamValidationParameters newParameters = new Builder().setCurPlayerIndex(parameters.getCurPlayerIndex() + 1)
					.setTeamSize(parameters.getTeamSize() + 1)
					.setNumberOfDefenders(parameters.getNumberOfDefenders() + (isDefender ? 1 : 0))
					.setNumberOfMidFielders(parameters.getNumberOfMidFielders() + (isMidFielder ? 1 : 0))
					.setNumberOfForwards(parameters.getNumberOfForwards() + (isForward ? 1 : 0))
					.setNumberOfGoalKeep(parameters.getNumberOfGoalKeep() + (isGoalKeeper ? 1 : 0))
					.getTeamValidationParameters();

			if (checkTeamIsValid(players, newParameters)) {
				return Boolean.TRUE;
			}
		}
		setVisitedState(parameters);
		return Boolean.FALSE;
	}

	private static Boolean isVisitedState(TeamValidationParameters parameters) {
		return (isVisited[parameters.getCurPlayerIndex()][parameters.getNumberOfDefenders()][parameters
				.getNumberOfMidFielders()][parameters.getNumberOfForwards()][parameters.getNumberOfGoalKeep()]);
	}

	private static void setVisitedState(TeamValidationParameters parameters) {
		isVisited[parameters.getCurPlayerIndex()][parameters.getNumberOfDefenders()][parameters
				.getNumberOfMidFielders()][parameters.getNumberOfForwards()][parameters
						.getNumberOfGoalKeep()] = Boolean.TRUE;
	}

	private static Boolean isValidSelection(TeamValidationParameters parameters) {
		return (isValidDefenders(parameters.getNumberOfDefenders())
				&& isValidMidFielders(parameters.getNumberOfMidFielders())
				&& isValidForwards(parameters.getNumberOfForwards())
				&& isValidGoalKeeper(parameters.getNumberOfGoalKeep()));
	}

	private static Boolean isValidDefenders(Integer defenders) {
		return (Constants.DEFENDERS_MIN <= defenders && defenders <= Constants.DEFENDERS_MAX);
	}

	private static Boolean isValidMidFielders(Integer midFielders) {
		return (Constants.MIDFIELDERS_MIN <= midFielders && midFielders <= Constants.MIDFIELDERS_MAX);
	}

	private static Boolean isValidForwards(Integer forwards) {
		return (Constants.FORWARDS_MIN <= forwards && forwards <= Constants.FORWARDS_MAX);
	}

	private static Boolean isValidGoalKeeper(Integer goalKeeper) {
		return goalKeeper == 1;
	}
}
