package com.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException() {
		super("Player does not exist.");
	}

	public PlayerNotFoundException(String message) {
		super(message);
	}
}
