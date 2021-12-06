package com.models;

/**
 * @author Jay Patel
 */
public enum StartOrResumeOptions {

	START_NEW_GAME(1, "1. Start New Game."),
	RESUME_PREVIOUS_GAME (2, "2. Resume Previous Game.");
	
	private Integer option;
	private String message;

	StartOrResumeOptions(Integer option, String message) {
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
