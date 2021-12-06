package com.exceptions;

/**
 * @author Jay Patel
 */
public class PlayerAlreadySelectedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlayerAlreadySelectedException() {
		super("Player is already selected and may not be available.");
	}

	public PlayerAlreadySelectedException(String message) {
		super(message);
	}
}
