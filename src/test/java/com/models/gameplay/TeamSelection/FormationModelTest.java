package com.models.gameplay.TeamSelection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class FormationModelTest {

	FormationModel formation = new FormationModel(4,4,2, FormationType.NEUTRAL);
	FormationModel formationExtended = new FormationModel(4,1,4,1, FormationType.ATTACKING);

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
	void InvalidFormationTest() {
		FormationModel formationModel = new FormationModel(5,2,3,FormationType.NEUTRAL);
		String nullFormation = "0-0-0";
		assertEquals(nullFormation,formationModel.toString());
	}

	@Test
	void InvalidFormationExtendedTest() {
		FormationModel formationModel = new FormationModel(4,2,3, 2,FormationType.NEUTRAL);
		String nullFormation = "0-0-0";
		assertEquals(nullFormation,formationModel.toString());
	}
}
