package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PlayerSelectionControllerTest {

	@Test
	void getSquadNeutralTest() {
		FormationModel formationModel = new FormationModel(4,4,2, FormationType.NEUTRAL);
		PlayerSelectionController playerSelectionController = new PlayerSelectionController(Arrays.asList(Constants.PLAYERS),formationModel);

		HashMap<PlayerModel, PlayingPosition> playing11 = playerSelectionController.getSquad();
		assertEquals(11,playing11.keySet().size());
	}
}