package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import com.gameplay.controller.PlayerSelectionController;
import com.models.FormationModel;
import com.models.FormationType;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerSelectionControllerTest {

	@Test
	void getSquadNeutralTest() {
		FormationModel formationModel = new FormationModel(4,4,2, FormationType.NEUTRAL);
		PlayerSelectionController playerSelectionController = new PlayerSelectionController(Arrays.asList(Constants.PLAYERS),formationModel);

		HashMap<PlayerModel, PlayingPosition> playing11 = playerSelectionController.getSquad();
		assertEquals(11,playing11.keySet().size());
	}
}