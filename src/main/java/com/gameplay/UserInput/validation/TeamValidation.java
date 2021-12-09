package com.gameplay.UserInput.validation;

import com.utils.CommonFunctions;
import com.utils.Constants;
import com.gameplay.entity.PlayerEntity;
import com.models.PlayerPositions;
import com.models.TeamValidationParameters;
import com.models.TeamValidationParameters.Builder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jay Patel
 */
public class TeamValidation implements ITeamValidation {

	private final Integer TEAM_SIZE = 11;

	private Boolean[][][][][] isVisited;

	public TeamValidation() {
		
	}
	
	public void init() {
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

	@Override
	public Boolean isTeamValid(List<PlayerEntity> players) {
		if (players == null || players.size() != TEAM_SIZE) {
			return Boolean.FALSE;
		}
		init();
		TeamValidationParameters parameters = new Builder().setCurPlayerIndex(0).setTeamSize(0).setNumberOfDefenders(0)
				.setNumberOfMidFielders(0).setNumberOfForwards(0).setNumberOfGoalKeep(0).getTeamValidationParameters();
		return checkTeamIsValid(players, parameters);
	}

	private Boolean checkTeamIsValid(List<PlayerEntity> players, TeamValidationParameters parameters) {
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

	private Boolean isVisitedState(TeamValidationParameters parameters) {
		return (isVisited[parameters.getCurPlayerIndex()][parameters.getNumberOfDefenders()][parameters
				.getNumberOfMidFielders()][parameters.getNumberOfForwards()][parameters.getNumberOfGoalKeep()]);
	}

	private void setVisitedState(TeamValidationParameters parameters) {
		isVisited[parameters.getCurPlayerIndex()][parameters.getNumberOfDefenders()][parameters
				.getNumberOfMidFielders()][parameters.getNumberOfForwards()][parameters
						.getNumberOfGoalKeep()] = Boolean.TRUE;
	}

	private Boolean isValidSelection(TeamValidationParameters parameters) {
		return (isValidDefenders(parameters.getNumberOfDefenders())
				&& isValidMidFielders(parameters.getNumberOfMidFielders())
				&& isValidForwards(parameters.getNumberOfForwards())
				&& isValidGoalKeeper(parameters.getNumberOfGoalKeep()));
	}

	private Boolean isValidDefenders(Integer defenders) {
		return (Constants.DEFENDERS_MIN <= defenders && defenders <= Constants.DEFENDERS_MAX);
	}

	private Boolean isValidMidFielders(Integer midFielders) {
		return (Constants.MIDFIELDERS_MIN <= midFielders && midFielders <= Constants.MIDFIELDERS_MAX);
	}

	private Boolean isValidForwards(Integer forwards) {
		return (Constants.FORWARDS_MIN <= forwards && forwards <= Constants.FORWARDS_MAX);
	}

	private Boolean isValidGoalKeeper(Integer goalKeeper) {
		return goalKeeper == 1;
	}
}
