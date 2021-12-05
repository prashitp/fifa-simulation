package com.exceptions;

/**
 * @author Jay Patel
 */
public class InvalidPlayingXISelectionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPlayingXISelectionException() {
		super("Invalid Playing XI Selection.");
	}

	public InvalidPlayingXISelectionException(String message) {
		super(message);
	}
}
