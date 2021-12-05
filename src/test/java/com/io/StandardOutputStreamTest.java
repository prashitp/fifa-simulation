package com.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.util.reflection.FieldSetter;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class StandardOutputStreamTest {

	private IOutputStream outputStream = StandardOutputStream.getInstance();
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() throws Exception {
		FieldSetter teamService = new FieldSetter(outputStream,
				StandardOutputStream.class.getDeclaredField("printStream"));
		teamService.set(new PrintStream(outputStreamCaptor));
	}

	@Test
	@Order(1)
	public void printlnOutputTest() {
		outputStream.println("hello");
		String[] output = outputStreamCaptor.toString().split(System.lineSeparator());
		assert (output[0].equals("hello"));
	}

	@Test
	@Order(2)
	public void printOutputTest() {
		outputStream.print("hello");
		assert (outputStreamCaptor.toString().equals("hello"));
	}
}
