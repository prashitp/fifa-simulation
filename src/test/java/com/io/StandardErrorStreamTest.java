package com.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StandardErrorStreamTest {
	public static IErrorStream errorStream;
	public static ByteArrayOutputStream baos;

	@BeforeAll
	public static void init() {
		baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setErr(printStream);
		errorStream = StandardErrorStream.getInstance();
	}

	@Test
	public void printlnOutputTest() {
		errorStream.printError("hello");
		String[] output = baos.toString().split(System.lineSeparator());
		String firstLine = "[ERROR] hello";
		assert (output.length == 1 && output[0].equals(firstLine));
	}

}
