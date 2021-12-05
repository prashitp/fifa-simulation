package com.exceptions;

public class InvalidInputException extends RuntimeException {

	public InvalidInputException() {
		super("Input is invalid.");
	}

	public InvalidInputException(String message) {
		super(message);
	}
}
