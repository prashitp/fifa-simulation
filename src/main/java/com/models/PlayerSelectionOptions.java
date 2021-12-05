package com.models;

/**
 * @author Jay Patel
 */
public enum PlayerSelectionOptions {

	AVAILABLE_PLAYERS(1, "1. Show Available Players."), SELECTED_PLAYERS(2, "2. Show Selected Players."),
	ADD_PLAYER(3, "3. Add Player."), REMOVE_PLAYER(4, "4. Remove Player."), DONE(5, "5. Done.");

	private Integer option;
	private String message;

	PlayerSelectionOptions(Integer option, String message) {
		this.option = option;
		this.message = message;
	}

	public Integer getOption() {
		return this.option;
	}

	public String getMessage() {
		return this.message;
	}
}
