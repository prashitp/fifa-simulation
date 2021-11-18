package com.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class StandardOutputStreamTest {

	public static IOutputStream outputStream;
	public static ByteArrayOutputStream baos;

	@BeforeAll
	public static void init() {
		baos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);
		outputStream = StandardOutputStream.getInstance();
	}

	@Test
	@Order(1)
	public void printlnOutputTest() {
		outputStream.println("hello");
		String[] output = baos.toString().split(System.lineSeparator());
		String firstLine = "hello";
		assert (output.length == 1 && output[0].equals(firstLine));
	}

	@Test
	@Order(2)
	public void printOutputTest() {
		outputStream.print("world");
		String[] output = baos.toString().split(System.lineSeparator());
		String secondLine = "world";
		assert (output.length == 2 && output[1].equals(secondLine));
	}
}
