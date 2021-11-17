//Author - Prashit Patel
package com.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {
	PlayerModel player= new PlayerModel(
			"Cristiano Ronaldo",
			new PlayerPositions[]{PlayerPositions.ST,PlayerPositions.LW},
			91,91,45000000,36,187,83,"Manchester United",
			PlayerPositions.ST,7,23,"Portugal","Right",5,
			PlayerWorkRate.HIGH, new String[]{"Power Free-Kick", "Flair", "Long Shot Taker (AI)", "Speed Dribbler (AI)", "Outside Foot Shot"},
			87,94,80,87,34,75,87,95,90,80,
			86,88,81,84,77,88,87,88,86,
			94,74,94,95,77,77,93,63,
			29,95,76,88,95,24,32,24,
			7,11,15,14,11,0
	);

	@Test
	void getPlayerNameTest() {
		String playerName = player.getPlayerName();
		assertEquals("Cristiano Ronaldo",playerName);
	}

	@Test
	void getPositionsTest() {
		PlayerPositions[] positions = player.getPositions();
		assertArrayEquals(new PlayerPositions[]{PlayerPositions.ST,PlayerPositions.LW},positions);
	}

	@Test
	void getHeightTest() {
		int height = player.getHeight();
		assertEquals(187,height);
	}

	@Test
	void getWeightTest() {
		int weight = player.getWeight();
		assertEquals(83,weight);
	}

	@Test
	void getClubPositionTest() {
		PlayerPositions clubPosition = player.getClubPosition();
		assertEquals(PlayerPositions.ST, clubPosition);
	}

	@Test
	void getNationalityTest() {
		String nationality = player.getNationality();
		assertEquals("Portugal",nationality);
	}

	@Test
	void getPreferredFoot() {
		String preferredFoot = player.getPreferredFoot();
		assertEquals("Right",preferredFoot);
	}

	@Test
	void getSkillMoves() {
		int skillMoves = player.getSkillMoves();
		assertEquals(5,skillMoves);
	}

	@Test
	void getWorkRate() {
		PlayerWorkRate workRate = player.getWorkRate();
		assertEquals(PlayerWorkRate.HIGH,workRate);
	}

	@Test
	void getPlayerTraits() {
		String[] traits = player.getPlayerTraits();
		String[] expectedTraits = new String[]{"Power Free-Kick", "Flair", "Long Shot Taker (AI)", "Speed Dribbler (AI)", "Outside Foot Shot"};
		assertArrayEquals(expectedTraits,traits);
	}
}