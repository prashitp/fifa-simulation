package com.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author prashitpatel
 */
class ClubModelTest {
	static HashMap<ClubAttributes,Integer> attributes;
	ClubModel club = new ClubModel(1,"Arsenal",attributes,80000000);
	@BeforeAll
	static void initialize() {
		attributes = new HashMap<>();
		attributes.put(ClubAttributes.OVERALL,79);
		attributes.put(ClubAttributes.ATTACK,79);
		attributes.put(ClubAttributes.MIDFIELD,79);
		attributes.put(ClubAttributes.DEFENCE,79);
	}

	@Test
	void getClubIdTest() {
		int clubId = club.getClubId();
		assertEquals(1,clubId);
	}

	@Test
	void getClubNameTest() {
		String clubName = club.getClubName();
		assertEquals("Arsenal",clubName);
	}

	@Test
	void getPlayersTest() {
		PlayerModel[] players = club.getPlayers();
		assertEquals("Arsenal",players[0].club);
	}
}