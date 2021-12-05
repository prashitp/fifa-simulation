package com.exceptions;

/**
 * @author Jay Patel
 */
public class TeamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TeamNotFoundException() {
		super("Team does not exist.");
	}

	public TeamNotFoundException(String message) {
		super(message);
	}
}
