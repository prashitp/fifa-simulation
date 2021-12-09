package com.gameplay.UserInput;

import com.utils.CommonFunctions;
import com.exceptions.InvalidInputException;
import com.exceptions.InvalidPlayingXISelectionException;
import com.exceptions.TeamNotFoundException;
import com.gameplay.UserInput.validation.ITeamValidation;
import com.gameplay.UserInput.validation.TeamValidation;
import com.gameplay.controller.*;
import com.gameplay.entity.PlayerEntity;
import com.io.*;
import com.models.*;
import com.utils.Constants;
import com.utils.TableFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jay Patel
 */
// Presentation Layer
public class UserInputFunction implements IUserInputFunction {

	public static final int TOTAL_SEASONS_PLAYED_IN_SIMULATION = 10;

	public static final String PRINT_TEAM_ID_SELECTION_MSG = "Select team id of any one of the above listed teams.";
	public static final String PRINT_AVAILABLE_TEAM_MSG = "[AVAILABLE TEAMS]:";
	public static final String PRINT_PLAYER_SELECTION_OPTIONS = "Please select 11 players.\n"
			+ PlayerSelectionOptions.AVAILABLE_PLAYERS.getMessage() + "\n"
			+ PlayerSelectionOptions.SELECTED_PLAYERS.getMessage() + "\n"
			+ PlayerSelectionOptions.ADD_PLAYER.getMessage() + "\n" + PlayerSelectionOptions.REMOVE_PLAYER.getMessage()
			+ "\n" + PlayerSelectionOptions.DONE.getMessage();
	public static final String PRINT_CUSTOMIZATION_OPTIONS = "Do you want to select playing 11?\n"
			+ BooleanOptions.YES.getMessage() + "\n" + BooleanOptions.NO.getMessage();
	public static final String OPTION_TO_RESUME_OR_START_OVER = "Do you want to resume the previous game or start a new Game?\n"
			+ StartOrResumeOptions.START_NEW_GAME.getMessage() + "\n"
			+ StartOrResumeOptions.RESUME_PREVIOUS_GAME.getMessage();

	private IOutputStream outputStream;
	private IInputStream inputStream;
	private IErrorStream errorStream;
	private IUserTeamController userTeamController;
	private ITeamController teamController;
	private IPlayerStatusController playerStatusController;
	private IUserPlayersController userPlayersController;
	private ITeamValidation teamValidation;

	public UserInputFunction() {
		outputStream = StandardOutputStream.getInstance();
		inputStream = StandardInputStream.getInstance();
		errorStream = StandardErrorStream.getInstance();
		userTeamController = new UserTeamController();
		teamController = new TeamController();
		playerStatusController = new PlayerStatusController();
		userPlayersController = new UserPlayersController();
		teamValidation = new TeamValidation();
	}

	@Override
	public Boolean teamSelection() {
		Boolean isResume = null;
		do {
			isResume = isResuming();
		} while (isResume == null);

		if (isResume) {
			return Boolean.TRUE;
		}
		deleteResumeGameData();
		startNewGame();

		return Boolean.TRUE;
	}

	private Boolean isResuming() {
		try {
			UserTeamModel userTeam = userTeamController.fetchUserTeamModel();
			if (userTeam != null && (userTeam.getSeasonPlayed() < TOTAL_SEASONS_PLAYED_IN_SIMULATION
					&& userTeam.getSeasonPlayed() > 0)) {
				outputStream.println(OPTION_TO_RESUME_OR_START_OVER);
				Integer option = inputStream.readInteger();
				if (option.equals(StartOrResumeOptions.START_NEW_GAME.getOption())) {
					return Boolean.FALSE;
				} else if (option.equals(StartOrResumeOptions.RESUME_PREVIOUS_GAME.getOption())) {
					return Boolean.TRUE;
				} else {
					throw new InvalidInputException();
				}
			} else {
				return Boolean.FALSE;
			}
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}

	private void deleteResumeGameData() {
		userTeamController.deleteUserTeamModel();
	}

	private Integer startNewGame() {
		// team id selection.
		Integer teamId = null;
		do {
			teamId = selectTeamId();
		} while (teamId == null);
		userTeamController.setUserTeam(teamId);

		// does user want to customize playing xi.
		Boolean customizePlayingXI = null;
		do {
			customizePlayingXI = customizePlayingXI();
		} while (customizePlayingXI == null);
		userTeamController.customizePlayingXI(customizePlayingXI);

		if (customizePlayingXI.equals(Boolean.FALSE)) {
			return teamId;
		}

		// if user want to customize, then select players
		List<PlayerEntity> players;
		do {
			players = selectPlayers(teamId);
		} while (Boolean.FALSE.equals(validateTeamSelection(players)));
		userPlayersController.setPlayingXI(players);

		return teamId;
	}

	private Boolean validateTeamSelection(List<PlayerEntity> players) {
		try {
			if (teamValidation.isTeamValid(players)) {
				return Boolean.TRUE;
			} else {
				throw new InvalidPlayingXISelectionException();
			}
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return Boolean.FALSE;
	}

	private Integer selectTeamId() {
		try {
			outputStream.println(PRINT_AVAILABLE_TEAM_MSG);
			displayAllTeams();
			outputStream.println(PRINT_TEAM_ID_SELECTION_MSG);
			Integer teamId = inputStream.readInteger();
			if (Boolean.FALSE.equals(teamController.isTeamIdExists(teamId))) {
				throw new TeamNotFoundException();
			}
			return teamId;
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}

	private void displayAllTeams() {
//		List<ClubModel> teamList = teamController.fetchAllTeams();
		List<ClubModel> teamList = Arrays.asList(Constants.CLUBS);
		List<String[]> teamTable = new ArrayList<>();
		String[] header = TableFormat.createTableRow("TEAM ID", "TEAM NAME");
		teamTable.add(header);
		for (ClubModel team : teamList) {
			String[] row = TableFormat.createTableRow(CommonFunctions.convertIntegerToString(team.getClubId()),
					team.getClubName());
			teamTable.add(row);
		}
		TableFormat.printTable(teamTable);
	}

	private Boolean customizePlayingXI() {
		try {
			outputStream.println(PRINT_CUSTOMIZATION_OPTIONS);
			Integer option = inputStream.readInteger();
			if (option.equals(BooleanOptions.YES.getOption())) {
				return Boolean.TRUE;
			} else if (option.equals(BooleanOptions.NO.getOption())) {
				return Boolean.FALSE;
			} else {
				throw new InvalidInputException();
			}
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}

	private List<PlayerEntity> selectPlayers(Integer teamId) {
		Boolean exit = Boolean.FALSE;
		List<PlayerEntity> selectedPlayerList = new ArrayList<>();
		List<PlayerEntity> availablePlayerList = playerStatusController.fetchAllPlayers(teamId);
		while (Boolean.FALSE.equals(exit)) {
			outputStream.println(PRINT_PLAYER_SELECTION_OPTIONS);
			try {
				Integer optionSelected = inputStream.readInteger();
				if (optionSelected == PlayerSelectionOptions.DONE.getOption()) { // exit
					exit = Boolean.TRUE;
					break;
				}
				Boolean invalidOption = Boolean.TRUE;
				for (PlayerSelectionOptions playerSelection : PlayerSelectionOptions.values()) {
					if (optionSelected.equals(playerSelection.getOption())) {
						invalidOption = Boolean.FALSE;
						playerSelection.getPlayerXISelection().executeSelection(selectedPlayerList,
								availablePlayerList);
					}
				}
				if (invalidOption) {
					throw new InvalidInputException();
				}
			} catch (Exception e) {
				errorStream.printError(e.getMessage());
			}
		}
		return selectedPlayerList;
	}
}
