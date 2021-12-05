package com.models.gameplay.TeamSelection;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class LineupTest {
	public static Lineup lineup;

	@BeforeAll
	public static void init() {
		FormationModel formationModel = new FormationModel(4,5,1,FormationType.DEFENSIVE);
		HashMap<PlayerModel,PlayingPosition> playing11 = new HashMap<>();
		playing11.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
		lineup = new Lineup(formationModel,playing11);
	}

	@Test
	void getFormationTest() {
		FormationModel formation = lineup.getFormation();
		assertEquals("4-5-1",formation.toString());
	}

	@Test
	void getPlaying11Test() {
		HashMap<PlayerModel, PlayingPosition> playing11 = lineup.getPlaying11();
		assertEquals(PlayingPosition.FORWARD,playing11.get(Constants.PLAYERS[0]));
	}
}