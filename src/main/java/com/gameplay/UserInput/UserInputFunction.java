package com.gameplay.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.CommonFunctions;
import com.exceptions.InvalidInputException;
import com.exceptions.InvalidPlayingXISelectionException;
import com.exceptions.PlayerAlreadySelectedException;
import com.exceptions.PlayerNotFoundException;
import com.exceptions.TeamNotFoundException;
import com.gameplay.UserInput.validation.TeamValidation;
import com.gameplay.controller.IPlayerStatusController;
import com.gameplay.controller.ITeamController;
import com.gameplay.controller.IUserPlayersController;
import com.gameplay.controller.IUserTeamController;
import com.gameplay.controller.PlayerStatusController;
import com.gameplay.controller.TeamController;
import com.gameplay.controller.UserPlayersController;
import com.gameplay.controller.UserTeamController;
import com.gameplay.entity.PlayerEntity;
import com.io.IErrorStream;
import com.io.IInputStream;
import com.io.IOutputStream;
import com.io.StandardErrorStream;
import com.io.StandardInputStream;
import com.io.StandardOutputStream;
import com.models.BooleanOptions;
import com.models.ClubModel;
import com.models.PlayerSelectionOptions;
import com.models.StartOrResumeOptions;
import com.models.UserTeamModel;
import com.utils.Converter;
import com.utils.TableFormat;

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
	public static final String AVAILABLE_PLAYERS = "[AVAILABLE PLAYERS]: ";
	public static final String SELECTED_PLAYERS = "[SELECTED PLAYERS]: ";
	public static final String OPTION_TO_RESUME_OR_START_OVER = "Do you want to resume the previous game or start a new Game?\n"
			+ StartOrResumeOptions.START_NEW_GAME.getMessage() + "\n"
			+ StartOrResumeOptions.RESUME_PREVIOUS_GAME.getMessage();
	public static final String INPUT_PLAYER_ID_MSG = "Please enter player id.";

	private IOutputStream outputStream;
	private IInputStream inputStream;
	private IErrorStream errorStream;
	private IUserTeamController userTeamController;
	private ITeamController teamController;
	private IPlayerStatusController playerStatusController;
	private IUserPlayersController userPlayersController;

	public UserInputFunction() {
		outputStream = StandardOutputStream.getInstance();
		inputStream = StandardInputStream.getInstance();
		errorStream = StandardErrorStream.getInstance();
		userTeamController = new UserTeamController();
		teamController = new TeamController();
		playerStatusController = new PlayerStatusController();
		userPlayersController = new UserPlayersController();
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
			if (userTeam != null && (userTeam.getSeasonPlayed() != TOTAL_SEASONS_PLAYED_IN_SIMULATION
					&& userTeam.getSeasonPlayed() != 0)) {
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
			if (TeamValidation.isTeamValid(players)) {
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
		List<ClubModel> teamList = teamController.fetchAllTeams();
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
		while (Boolean.FALSE.equals(exit)) {
			outputStream.println(PRINT_PLAYER_SELECTION_OPTIONS);
			try {
				Integer optionSelected = inputStream.readInteger();
				if (optionSelected.equals(PlayerSelectionOptions.AVAILABLE_PLAYERS.getOption())) {
					displayAvailablePlayers(teamId, selectedPlayerList);
				} else if (optionSelected.equals(PlayerSelectionOptions.SELECTED_PLAYERS.getOption())) {
					displaySelectedPlayers(selectedPlayerList);
				} else if (optionSelected.equals(PlayerSelectionOptions.ADD_PLAYER.getOption())) {
					PlayerEntity player = selectPlayer(selectedPlayerList);
					if (player == null) {
						continue;
					}
					selectedPlayerList.add(player);
					outputStream.println("[Player with id " + Converter.convertToPlayerIdInteger(player.getPlayerId())
							+ " added.]\n");
				} else if (optionSelected.equals(PlayerSelectionOptions.REMOVE_PLAYER.getOption())) {
					PlayerEntity player = removePlayer(selectedPlayerList);
					if (player == null) {
						continue;
					}
					selectedPlayerList.remove(player);
					outputStream.println("[Player with id " + Converter.convertToPlayerIdInteger(player.getPlayerId())
							+ " removed.]\n");
				} else if (optionSelected == PlayerSelectionOptions.DONE.getOption()) { // exit
					exit = Boolean.TRUE;
				} else {
					throw new InvalidInputException();
				}
			} catch (Exception e) {
				errorStream.printError(e.getMessage());
			}
		}
		return selectedPlayerList;
	}

	private void displayAvailablePlayers(Integer teamId, List<PlayerEntity> selectedPlayerList) {
		outputStream.println(AVAILABLE_PLAYERS);
		List<PlayerEntity> allPlayers = playerStatusController.fetchAllPlayers(teamId);
		List<PlayerEntity> availablePlayerList = allPlayers.stream()
				.filter(player -> Boolean.FALSE.equals(isPlayerAlreadySelected(player, selectedPlayerList)))
				.collect(Collectors.toList());
		TableFormat.displayPlayers(availablePlayerList);
	}

	private Boolean isPlayerAlreadySelected(PlayerEntity player, List<PlayerEntity> selectedPlayers) {
		return Boolean.FALSE.equals(selectedPlayers.stream().filter(p -> p.getPlayerId().equals(player.getPlayerId()))
				.collect(Collectors.toList()).isEmpty());
	}

	private void displaySelectedPlayers(List<PlayerEntity> selectedPlayerList) {
		outputStream.println(SELECTED_PLAYERS);
		TableFormat.displayPlayers(selectedPlayerList);
	}

	private PlayerEntity selectPlayer(List<PlayerEntity> selectedPlayers) {
		try {
			outputStream.println(INPUT_PLAYER_ID_MSG);
			Integer playerId = inputStream.readInteger();
			PlayerEntity player = playerStatusController.fetchPlayer(playerId);
			if (player == null) {
				throw new PlayerNotFoundException();
			} else if (isPlayerAlreadySelected(player, selectedPlayers)) {
				throw new PlayerAlreadySelectedException();
			} else {
				return player;
			}
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}

	private PlayerEntity removePlayer(List<PlayerEntity> selectedPlayerList) {
		try {
			outputStream.println(INPUT_PLAYER_ID_MSG);
			Integer playerId = inputStream.readInteger();
			List<PlayerEntity> matchedPlayers = selectedPlayerList.stream()
					.filter(player -> (Converter.convertToPlayerIdInteger(player.getPlayerId())).equals(playerId))
					.collect(Collectors.toList());
			if (matchedPlayers.isEmpty()) {
				throw new PlayerNotFoundException();
			}
			return matchedPlayers.get(0);
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}
}
