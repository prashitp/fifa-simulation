//Author - Prashit Patel
package com.models;

import com.exceptions.FormationInvalidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormationModelTest {

	FormationModel formation = new FormationModel(4,4,2);
	FormationModel formationExtended = new FormationModel(4,1,4,1);

	FormationModelTest() throws FormationInvalidException {}

	@Test
	void testToStringTest() {
		String formationString = formation.toString();
		String formationExtendedString = formationExtended.toString();
		assertEquals("4-4-2",formationString);
		assertEquals("4-1-4-1",formationExtendedString);
	}

	@Test
	void getFormationTest() {
		List<Integer> formationList = formation.getFormation();
		List<Integer> formationExtendedList = formationExtended.getFormation();

		List<Integer> expectedFormationList = Arrays.asList(4,4,2);
		List<Integer> expectedFormationExtendedList = Arrays.asList(4,1,4,1);

		assertEquals(expectedFormationList,formationList);
		assertEquals(expectedFormationExtendedList,formationExtendedList);
	}

	@Test
	void invalidFormationTest() {
		Exception exp = assertThrows(FormationInvalidException.class, () ->
				new FormationModel(3,6,1)
		);
		assertEquals("Invalid Formation", exp.getMessage());
	}

	@Test
	void invalidFormationExtendedTest() {
		Exception exp = assertThrows(FormationInvalidException.class, () ->
				new FormationModel(4,3,2,2)
		);
		assertEquals("Invalid Formation", exp.getMessage());
	}
}
