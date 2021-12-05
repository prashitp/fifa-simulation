package com.exceptions;

public class InvalidPlayingXISelectionException extends RuntimeException {

	public InvalidPlayingXISelectionException() {
		super("Invalid Playing XI Selection.");
	}

	public InvalidPlayingXISelectionException(String message) {
		super(message);
	}
}
