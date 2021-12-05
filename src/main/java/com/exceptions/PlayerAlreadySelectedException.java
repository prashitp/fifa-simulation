package com.exceptions;

public class PlayerAlreadySelectedException extends RuntimeException {

	public PlayerAlreadySelectedException() {
		super("Player is already selected and may not be available.");
	}

	public PlayerAlreadySelectedException(String message) {
		super(message);
	}
}
