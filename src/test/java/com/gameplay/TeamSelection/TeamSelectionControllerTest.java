package com.gameplay.TeamSelection;

import com.models.Lineup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class TeamSelectionControllerTest {
	ITeamSelectionController iTeamSelectionController = new TeamSelectionController("Chelsea", "Arsenal");

	@Test
	public void getSquadsTest() {
		List<Lineup> lineup = iTeamSelectionController.getSquads();
		assertEquals(11,lineup.get(0).getPlaying11().size());
	}
}