package com.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author prashitpatel
 */
class DefaultFormationsTest {

	@Test
	public void getFormationsTest() {
		FormationModel[] formations = DefaultFormations.getFormations();
		FormationModel formationModel = new FormationModel(4,4,2, FormationType.NEUTRAL);
		assertEquals(formationModel.getFormation(), formations[0].getFormation());
	}

}