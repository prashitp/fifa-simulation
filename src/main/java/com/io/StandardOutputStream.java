package com.io;

import java.io.PrintStream;

/**
 * @author Jay Patel
 */
public class StandardOutputStream implements OutputStream {

	private PrintStream printStream;
	private static StandardOutputStream outputStream;

	private StandardOutputStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public static StandardOutputStream getInstance() {
		if (outputStream == null) {
			outputStream = new StandardOutputStream(System.out);
		}
		return outputStream;
	}

	@Override
	public void print(Object o) {
		printStream.print(o);
		printStream.flush();
	}

	@Override
	public void println(Object o) {
		printStream.println(o);
		printStream.flush();
	}

	@Override
	protected void finalize() {
		printStream.close();
	}
}
