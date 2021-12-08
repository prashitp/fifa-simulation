package com.models;

import com.models.FormationModel;
import com.models.FormationType;
import com.models.DefaultFormations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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