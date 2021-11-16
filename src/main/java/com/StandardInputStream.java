package com;

/**
 * @author Jay Patel
 */
public class StandardInputStream implements InputStream {

	private static StandardInputStream inputStream;

	private StandardInputStream() {
	}

	public static InputStream getInstance() {
		inputStream = new StandardInputStream();
		return inputStream;
	}

	@Override
	public String readLine() {
		return "";
	}

	@Override
	public Integer readInteger() {
		return 0;
	}

	@Override
	public Double readDouble() {
		return 0.0D;
	}

	@Override
	public Long readLong() {
		return 0L;
	}
}
