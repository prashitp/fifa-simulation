package com.models;

import com.utils.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author prashitpatel
 */
class LineupTest {
	public static Lineup lineup;

	@BeforeAll
	public static void init() {
		FormationModel formationModel = new FormationModel(4,5,1, FormationType.DEFENSIVE);
		ClubModel club= Constants.CLUBS[0];
		HashMap<PlayerModel,PlayingPosition> playing11 = new HashMap<>();
		playing11.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
		lineup = new Lineup(club,formationModel,playing11);
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