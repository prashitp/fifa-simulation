package com.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Jay Patel
 */
public class StandardErrorStreamTest {

	private IErrorStream errorStream = StandardErrorStream.getInstance();
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void init() throws Exception {
		FieldSetter teamService = new FieldSetter(errorStream,
				StandardErrorStream.class.getDeclaredField("printStream"));
		teamService.set(new PrintStream(outputStreamCaptor));
	}

	@Test
	public void printlnOutputTest() {
		errorStream.printError("hello");
		String[] output = outputStreamCaptor.toString().split(System.lineSeparator());
		String firstLine = "[ERROR] hello";
		assert (output.length == 1 && output[0].equals(firstLine));
	}

}
