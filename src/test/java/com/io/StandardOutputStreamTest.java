package com.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * @author Jay Patel
 */
public class StandardOutputStreamTest {

	@Test
	public void outputStreamTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);

		IOutputStream outputStream = StandardOutputStream.getInstance();

		outputStream.println("hello");
		outputStream.print("world");
		String[] output = baos.toString().split(System.lineSeparator());

		String firstLine = "hello";
		String secondLine = "world";

		assert (output.length == 2 && output[0].equals(firstLine) && output[1].equals(secondLine));
	}

}
