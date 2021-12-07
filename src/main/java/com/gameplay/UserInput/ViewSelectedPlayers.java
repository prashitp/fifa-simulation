package com.gameplay.UserInput;

import java.util.List;

import com.gameplay.entity.PlayerEntity;
import com.io.IOutputStream;
import com.io.StandardOutputStream;
import com.utils.TableFormat;

public class ViewSelectedPlayers extends PlayingXISelection {

	public static final String SELECTED_PLAYERS = "[SELECTED PLAYERS]: ";

	private IOutputStream outputStream;

	public ViewSelectedPlayers() {
		outputStream = StandardOutputStream.getInstance();
	}

	@Override
	public Boolean executeSelection(List<PlayerEntity> selectedPlayers, List<PlayerEntity> availablePlayers) {
		outputStream.println(SELECTED_PLAYERS);
		TableFormat.displayPlayers(selectedPlayers);
		return Boolean.TRUE;
	}

}
