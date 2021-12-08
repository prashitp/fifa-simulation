package com.gameplay.UserInput;

import java.util.List;
import java.util.stream.Collectors;

import com.gameplay.entity.PlayerEntity;
import com.io.IOutputStream;
import com.io.StandardOutputStream;
import com.utils.TableFormat;

/**
 * @author Jay Patel
 */
public class ViewAvailablePlayers extends PlayingXISelection {

	public static final String AVAILABLE_PLAYERS = "[AVAILABLE PLAYERS]: ";

	private IOutputStream outputStream;

	public ViewAvailablePlayers() {
		outputStream = StandardOutputStream.getInstance();
	}

	@Override
	public Boolean executeSelection(List<PlayerEntity> selectedPlayers, List<PlayerEntity> availablePlayers) {
		outputStream.println(AVAILABLE_PLAYERS);
		List<PlayerEntity> availablePlayerList = availablePlayers.stream()
				.filter(player -> Boolean.FALSE.equals(isPlayerAlreadySelected(player, selectedPlayers)))
				.collect(Collectors.toList());
		TableFormat.displayPlayers(availablePlayerList);
		return Boolean.TRUE;
	}

	private Boolean isPlayerAlreadySelected(PlayerEntity player, List<PlayerEntity> selectedPlayers) {
		return Boolean.FALSE.equals(selectedPlayers.stream().filter(p -> p.getPlayerId().equals(player.getPlayerId()))
				.collect(Collectors.toList()).isEmpty());
	}
}
