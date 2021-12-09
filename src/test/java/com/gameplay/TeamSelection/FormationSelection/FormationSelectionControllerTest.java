package com.gameplay.TeamSelection.FormationSelection;

import com.utils.Constants;
import com.gameplay.controller.FormationSelectionController;
import com.models.FormationModel;
import com.models.FormationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class FormationSelectionControllerTest {

	public static FormationSelectionController formationSelectionController;
	@BeforeAll
	public static void init() {
		formationSelectionController = new FormationSelectionController(Constants.CLUBS[0], Constants.CLUBS[1]);
	}

	@Test
	void getFormationTest() {
		FormationModel formationModel = formationSelectionController.getFormation();
		assertEquals(FormationType.ATTACKING, formationModel.type);
	}
}