package com.io;

import java.util.Scanner;

/**
 * @author Jay Patel
 */
public class StandardInputStream implements InputStream {

	private Scanner scanner;
	private static StandardInputStream inputStream;

	private StandardInputStream(Scanner scanner) {
		this.scanner = scanner;
	}

	public static InputStream getInstance() {
		if (inputStream == null) {
			inputStream = new StandardInputStream(new Scanner(System.in));
		}
		return inputStream;
	}

	@Override
	public String readLine() {
		return scanner.nextLine();
	}

	@Override
	public Integer readInteger() {
		return Integer.parseInt(readLine());
	}

	@Override
	public Double readDouble() {
		return Double.parseDouble(readLine());
	}

	@Override
	public Long readLong() {
		return Long.parseLong(readLine());
	}

	@Override
	protected void finalize() {
		scanner.close();
	}
}
