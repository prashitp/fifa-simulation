package com;

/**
 * @author Jay Patel
 */
public class StandardOutputStream implements OutputStream {

	private static StandardOutputStream outputStream;

	private StandardOutputStream() {
	}

	public static StandardOutputStream getInstance() {
		outputStream = new StandardOutputStream();
		return outputStream;
	}

	@Override
	public void print(Object o) {
	}

	@Override
	public void println(Object o) {
	}
}
