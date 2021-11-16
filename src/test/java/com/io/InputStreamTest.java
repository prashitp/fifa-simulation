package com.io;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

/**
 * @author Jay Patel
 */
public class InputStreamTest {

	@Test
	public void stringInputTestCase() {
		String userInput = String.format("hello world\n150\n10000000000\n1.25", System.lineSeparator());

		ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(bais);

		String expectedString = "hello world";
		Integer expectedInteger = 150;
		Long expectedLong = 10000000000L;
		Double expectedDouble = 1.25D;

		InputStream inputStream = StandardInputStream.getInstance();

		String actualString = inputStream.readLine();
		Integer actualInteger = inputStream.readInteger();
		Long actualLong = inputStream.readLong();
		Double actualDouble = inputStream.readDouble();

		assert (expectedString.equals(actualString));
		assert (expectedInteger.equals(actualInteger));
		assert (expectedLong.equals(actualLong));
		assert (expectedDouble.equals(actualDouble));
	}
}