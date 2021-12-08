package com.gameplay.UserInput;

import java.util.List;
import java.util.stream.Collectors;

import com.exceptions.PlayerAlreadySelectedException;
import com.exceptions.PlayerNotFoundException;
import com.gameplay.entity.PlayerEntity;
import com.io.IErrorStream;
import com.io.IInputStream;
import com.io.IOutputStream;
import com.io.StandardErrorStream;
import com.io.StandardInputStream;
import com.io.StandardOutputStream;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
public class AddPlayer extends PlayingXISelection {

	public static final String INPUT_PLAYER_ID_MSG = "Please enter player id.";

	private IOutputStream outputStream;
	private IInputStream inputStream;
	private IErrorStream errorStream;

	public AddPlayer() {
		outputStream = StandardOutputStream.getInstance();
		inputStream = StandardInputStream.getInstance();
		errorStream = StandardErrorStream.getInstance();
	}

	@Override
	public Boolean executeSelection(List<PlayerEntity> selectedPlayers, List<PlayerEntity> availablePlayers) {
		try {
			outputStream.println(INPUT_PLAYER_ID_MSG);
			Integer playerId = inputStream.readInteger();
			PlayerEntity player = fetchPlayer(availablePlayers, playerId);
			if (player == null) {
				throw new PlayerNotFoundException();
			} else if (isPlayerAlreadySelected(player, selectedPlayers)) {
				throw new PlayerAlreadySelectedException();
			} else {
				selectedPlayers.add(player);
				outputStream.println("[Player with id " + Converter.convertToPlayerIdInteger(player.getPlayerId())
				+ " added.]\n");
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return Boolean.FALSE;
	}

	private PlayerEntity fetchPlayer(List<PlayerEntity> players, Integer playerId) {
		PlayerEntity player = null;
		for (PlayerEntity p : players) {
			if (Converter.convertToPlayerIdInteger(p.getPlayerId()).equals(playerId)) {
				player = p;
			}
		}
		return player;
	}

	private Boolean isPlayerAlreadySelected(PlayerEntity player, List<PlayerEntity> selectedPlayers) {
		return Boolean.FALSE.equals(selectedPlayers.stream().filter(p -> p.getPlayerId().equals(player.getPlayerId()))
				.collect(Collectors.toList()).isEmpty());
	}

}
