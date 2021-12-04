package com.io;

import java.io.PrintStream;

public class StandardErrorStream implements IErrorStream {

	private PrintStream printStream;
	private static StandardErrorStream errorStream;

	private StandardErrorStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public static StandardErrorStream getInstance() {
		if (errorStream == null) {
			errorStream = new StandardErrorStream(System.err);
		}
		return errorStream;
	}

	@Override
	public void printError(Object o) {
		printStream.println("[ERROR] " + o);
		printStream.flush();
	}
}
