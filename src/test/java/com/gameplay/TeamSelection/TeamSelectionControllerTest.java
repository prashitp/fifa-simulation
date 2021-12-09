package com.gameplay.TeamSelection;

import com.gameplay.controller.ITeamSelectionController;
import com.gameplay.controller.TeamSelectionController;
import com.models.Lineup;
import com.utils.Constants;
import org.junit.jupiter.api.Test;

import java.lang.constant.Constable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class TeamSelectionControllerTest {
	ITeamSelectionController iTeamSelectionController = new TeamSelectionController(Constants.CLUBS[0], Constants.CLUBS[1]);

	@Test
	public void getSquadsTest() {
		List<Lineup> lineup = iTeamSelectionController.getSquads();
		assertEquals(11,lineup.get(0).getPlaying11().size());
	}
}