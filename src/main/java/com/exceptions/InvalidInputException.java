package com.exceptions;

/**
 * @author Jay Patel
 */
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super("Input is invalid.");
	}

	public InvalidInputException(String message) {
		super(message);
	}
}
