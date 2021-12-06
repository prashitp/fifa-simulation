package com.gameplay.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.CommonFunctions;
import com.Constants;
import com.Constants.UserInput;
import com.entity.PlayerEntity;
import com.exceptions.InvalidInputException;
import com.exceptions.InvalidPlayingXISelectionException;
import com.exceptions.PlayerAlreadySelectedException;
import com.exceptions.PlayerNotFoundException;
import com.exceptions.TeamNotFoundException;
import com.gameplay.UserInput.validation.TeamValidation;
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
public class UserInputService implements IUserInputService {

	private ITeamService teamService;

	private IPlayerStatusService playerStatusService;

	private IUserTeamService userTeamService;

	private IUserPlayersService userPlayersService;

	private IOutputStream outputStream;

	private IInputStream inputStream;

	private IErrorStream errorStream;

	public UserInputService() {
		teamService = new TeamService();
		playerStatusService = new PlayerStatusService();
		userTeamService = new UserTeamService();
		userPlayersService = new UserPlayersService();
		outputStream = StandardOutputStream.getInstance();
		inputStream = StandardInputStream.getInstance();
		errorStream = StandardErrorStream.getInstance();
	}

	@Override
	public Integer selectTeamId() {
		try {
			outputStream.println(UserInput.PRINT_AVAILABLE_TEAM_MSG);
			displayAllTeams();
			outputStream.println(UserInput.PRINT_TEAM_ID_SELECTION_MSG);
			Integer teamId = inputStream.readInteger();
			if (Boolean.FALSE.equals(teamService.isTeamIdExists(teamId))) {
				throw new TeamNotFoundException();
			}
			userTeamService.setUserTeam(teamId);
			return teamId;
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}

	private void displayAllTeams() {
		List<ClubModel> teamList = teamService.fetchAllTeams();
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

	@Override
	public Boolean selectPlayingXI(Integer teamId) {
		try {
			List<PlayerEntity> selectedPlayerList = selectPlayers(teamId);
			if (TeamValidation.isTeamValid(selectedPlayerList)) {
				userPlayersService.setUsersPlayingXI(selectedPlayerList);
			} else {
				throw new InvalidPlayingXISelectionException();
			}
			return Boolean.TRUE;
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return Boolean.FALSE;
	}

	private List<PlayerEntity> selectPlayers(Integer teamId) {
		Boolean exit = Boolean.FALSE;
		List<PlayerEntity> selectedPlayerList = new ArrayList<>();
		while (Boolean.FALSE.equals(exit)) {
			outputStream.println(UserInput.PRINT_PLAYER_SELECTION_OPTIONS);
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

	private PlayerEntity selectPlayer(List<PlayerEntity> selectedPlayers) {
		try {
			outputStream.println(UserInput.INPUT_PLAYER_ID_MSG);
			Integer playerId = inputStream.readInteger();
			PlayerEntity player = playerStatusService.fetchPlayer(playerId); // fetch player by id;
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

	private void displayAvailablePlayers(Integer teamId, List<PlayerEntity> selectedPlayerList) {
		outputStream.println(UserInput.AVAILABLE_PLAYERS);
		List<PlayerEntity> allPlayers = playerStatusService.fetchAllPlayers(teamId);
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
		outputStream.println(UserInput.SELECTED_PLAYERS);
		TableFormat.displayPlayers(selectedPlayerList);
	}

	private PlayerEntity removePlayer(List<PlayerEntity> selectedPlayerList) {
		try {
			outputStream.println(UserInput.INPUT_PLAYER_ID_MSG);
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

	@Override
	public Boolean customizePlayingXI() {
		try {
			outputStream.println(UserInput.PRINT_CUSTOMIZATION_OPTIONS);
			Integer option = inputStream.readInteger();
			if (option.equals(BooleanOptions.YES.getOption())) {
				userTeamService.customizePlayingXI(Boolean.TRUE);
				return Boolean.TRUE;
			} else if (option.equals(BooleanOptions.NO.getOption())) {
				userTeamService.customizePlayingXI(Boolean.FALSE);
				return Boolean.FALSE;
			} else {
				throw new InvalidInputException();
			}
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean startNewGame() {
		if (isResuming()) {
			return Boolean.FALSE;
		}

		// delete all the player_status
		playerStatusService.deleteAllPlayers();

		// delete user_team
		userTeamService.deleteUserTeam();

		// copy all the player into player_status
		playerStatusService.copyFromPlayerToPlayerStatus();

		// update user_team
		userTeamService.customizePlayingXI(Boolean.FALSE);
		userTeamService.setSeasonPlayed(0);

		return Boolean.TRUE;
	}

	private Boolean isResuming() {
		try {
			UserTeamModel userTeam = userTeamService.getUserTeam();
			if (userTeam != null && (userTeam.getSeasonPlayed() != Constants.TOTAL_SEASONS_PLAYED_IN_SIMULATION
					&& userTeam.getSeasonPlayed() != 0)) {
				outputStream.println(UserInput.OPTION_TO_RESUME_OR_START_OVER);
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
		return Boolean.FALSE;
	}
}