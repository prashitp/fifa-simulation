package com;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * @author Jay Patel
 */
public class OutputStreamTest {

	@Test
	public void testOutputStream() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		OutputStream outputStream = StandardOutputStream.getInstance();

		outputStream.println("hello");
		outputStream.print("world");
		String[] output = baos.toString().split(System.lineSeparator());

		String firstLine = "hello";
		String secondLine = "world";

		assert (output.length == 2 && output[0].equals(firstLine) && output[1].equals(secondLine));
	}

}
