package com.models;

import com.gameplay.UserInput.AddPlayer;
import com.gameplay.UserInput.PlayingXISelection;
import com.gameplay.UserInput.RemovePlayer;
import com.gameplay.UserInput.ViewAvailablePlayers;
import com.gameplay.UserInput.ViewSelectedPlayers;

/**
 * @author Jay Patel
 */
public enum PlayerSelectionOptions {

	AVAILABLE_PLAYERS(1, "1. Show Available Players.", new ViewAvailablePlayers()),
	SELECTED_PLAYERS(2, "2. Show Selected Players.", new ViewSelectedPlayers()),
	ADD_PLAYER(3, "3. Add Player.", new AddPlayer()), REMOVE_PLAYER(4, "4. Remove Player.", new RemovePlayer()),
	DONE(5, "5. Done.", null);

	private Integer option;
	private String message;
	private PlayingXISelection playingXISelection;

	PlayerSelectionOptions(Integer option, String message, PlayingXISelection playingXISelection) {
		this.option = option;
		this.message = message;
		this.playingXISelection = playingXISelection;
	}

	public Integer getOption() {
		return this.option;
	}

	public String getMessage() {
		return this.message;
	}
	
	public PlayingXISelection getPlayerXISelection() {
		return this.playingXISelection;
	}
}
