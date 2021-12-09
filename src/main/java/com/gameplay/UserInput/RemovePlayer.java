package com.gameplay.UserInput;

import com.exceptions.PlayerNotFoundException;
import com.gameplay.entity.PlayerEntity;
import com.io.*;
import com.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jay Patel
 */
public class RemovePlayer extends PlayingXISelection {

	public static final String INPUT_PLAYER_ID_MSG = "Please enter player id.";

	private IOutputStream outputStream;
	private IInputStream inputStream;
	private IErrorStream errorStream;

	public RemovePlayer() {
		outputStream = StandardOutputStream.getInstance();
		inputStream = StandardInputStream.getInstance();
		errorStream = StandardErrorStream.getInstance();
	}

	@Override
	public Boolean executeSelection(List<PlayerEntity> selectedPlayers, List<PlayerEntity> availablePlayers) {
		try {
			outputStream.println(INPUT_PLAYER_ID_MSG);
			Integer playerId = inputStream.readInteger();
			List<PlayerEntity> matchedPlayers = selectedPlayers.stream()
					.filter(player -> (Converter.convertToPlayerIdInteger(player.getPlayerId())).equals(playerId))
					.collect(Collectors.toList());
			if (matchedPlayers.isEmpty()) {
				throw new PlayerNotFoundException();
			}
			selectedPlayers.remove(matchedPlayers.get(0));
			outputStream.println("[Player with id "
					+ Converter.convertToPlayerIdInteger(matchedPlayers.get(0).getPlayerId()) + " removed.]\n");
			return Boolean.TRUE;
		} catch (Exception e) {
			errorStream.printError(e.getMessage());
		}
		return Boolean.FALSE;
	}
}
