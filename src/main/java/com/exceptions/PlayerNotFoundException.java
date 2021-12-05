package com.exceptions;

/**
 * @author Jay Patel
 */
public class PlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException() {
		super("Player does not exist.");
	}

	public PlayerNotFoundException(String message) {
		super(message);
	}
}
