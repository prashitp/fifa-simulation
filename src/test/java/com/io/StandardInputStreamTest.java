package com.io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

/**
 * @author Jay Patel
 */
@TestMethodOrder(OrderAnnotation.class)
public class StandardInputStreamTest {

	public static IInputStream inputStream;

	@BeforeAll
	public static void init() {
		inputStream = Mockito.mock(IInputStream.class);
	}

	@Test
	@Order(1)
	public void stringInputTest() {
		String expectedString = "hello world";
		Mockito.when(inputStream.readLine()).thenReturn(expectedString);
		String actualString = inputStream.readLine();
		assert (expectedString.equals(actualString));
	}

	@Test
	@Order(2)
	public void integerInputTest() {
		Integer expectedInteger = 150;
		Mockito.when(inputStream.readInteger()).thenReturn(expectedInteger);
		Integer actualInteger = inputStream.readInteger();
		assert (expectedInteger.equals(actualInteger));
	}

	@Test
	@Order(3)
	public void longInputTest() {
		Long expectedLong = 10000000000L;
		Mockito.when(inputStream.readLong()).thenReturn(expectedLong);
		Long actualLong = inputStream.readLong();
		assert (expectedLong.equals(actualLong));
	}

	@Test
	@Order(4)
	public void doubleInputTest() {
		Double expectedDouble = 1.25D;
		Mockito.when(inputStream.readDouble()).thenReturn(expectedDouble);
		Double actualDouble = inputStream.readDouble();
		assert (expectedDouble.equals(actualDouble));
	}
}