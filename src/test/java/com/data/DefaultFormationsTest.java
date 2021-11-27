package com.data;

import com.exceptions.FormationInvalidException;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import com.models.gameplay.TeamSelection.DefaultFormations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class DefaultFormationsTest {

	@Test
	public void getFormationsTest() {
		FormationModel[] formations = DefaultFormations.getFormations();
		FormationModel formationModel = new FormationModel(4,4,2, FormationType.Neutral);
		assertEquals(formationModel.getFormation(), formations[0].getFormation());
	}

}