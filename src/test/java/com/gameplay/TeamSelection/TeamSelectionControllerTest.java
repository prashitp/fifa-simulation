package com.gameplay.TeamSelection;

import com.models.gameplay.TeamSelection.Lineup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class TeamSelectionControllerTest {
	ITeamSelectionController iTeamSelectionController = new TeamSelectionController("Chelsea", "Arsenal");

	@Test
	public void getTeamTest() {
		Lineup lineup = iTeamSelectionController.getTeam();
		assertEquals(11,lineup.getplaying11().size());
	}

}