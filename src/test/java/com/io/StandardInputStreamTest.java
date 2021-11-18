package com.io;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class StandardInputStreamTest {

	public static IInputStream inputStream;

	@BeforeAll
	public static void init() {
		String userInput = String.format("hello world\n150\n10000000000\n1.25", System.lineSeparator());
		ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(bais);
		inputStream = StandardInputStream.getInstance();
	}

	@Test
	@Order(1)
	public void stringInputTest() {
		String expectedString = "hello world";
		String actualString = inputStream.readLine();
		assert (expectedString.equals(actualString));
	}

	@Test
	@Order(2)
	public void integerInputTest() {
		Integer expectedInteger = 150;
		Integer actualInteger = inputStream.readInteger();
		assert (expectedInteger.equals(actualInteger));
	}

	@Test
	@Order(3)
	public void longInputTest() {
		Long expectedLong = 10000000000L;
		Long actualLong = inputStream.readLong();
		assert (expectedLong.equals(actualLong));
	}

	@Test
	@Order(4)
	public void doubleInputTest() {
		Double expectedDouble = 1.25D;
		Double actualDouble = inputStream.readDouble();
		assert (expectedDouble.equals(actualDouble));
	}
}