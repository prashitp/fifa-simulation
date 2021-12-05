package com.exceptions;

public class TeamNotFoundException extends RuntimeException {

	public TeamNotFoundException() {
		super("Team does not exist.");
	}

	public TeamNotFoundException(String message) {
		super(message);
	}
}
