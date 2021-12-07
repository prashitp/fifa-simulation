package com.models;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel and vasugamdha
 */
class PlayerModelTest {
	static HashMap<PlayerAttributes,Integer> skills;
	PlayerModel player= new PlayerModel(1,
			"Cristiano Ronaldo",
			new PlayerPositions[]{PlayerPositions.ST,PlayerPositions.LW},
			91,91,45000000,36,187,83,"Manchester United",
			PlayerPositions.ST,7,23,"Portugal","Right",5,
			PlayerWorkRate.HIGH, new String[]{"Power Free-Kick", "Flair", "Long Shot Taker (AI)", "Speed Dribbler (AI)", "Outside Foot Shot"},
			skills, false , 0, true
	);

	@BeforeAll
	static void initialize() {
		skills = new HashMap<>();
		skills.put(PlayerAttributes.PACE, 80);
		skills.put(PlayerAttributes.SHOOTING, 80);
		skills.put(PlayerAttributes.PASSING, 80);
		skills.put(PlayerAttributes.DRIBBLING, 80);
		skills.put(PlayerAttributes.DEFENDING, 80);
		skills.put(PlayerAttributes.PHYSIC, 80);
		skills.put(PlayerAttributes.ATTACKING_CROSSING, 80);
		skills.put(PlayerAttributes.ATTACKING_FINISHING, 80);
		skills.put(PlayerAttributes.ATTACKING_HEADING_ACCURACY, 80);
		skills.put(PlayerAttributes.ATTACKING_SHORT_PASSING, 80);
		skills.put(PlayerAttributes.ATTACKING_VOLLEYS, 80);
		skills.put(PlayerAttributes.SKILL_DRIBBLING, 80);
		skills.put(PlayerAttributes.SKILL_CURVE, 80);
		skills.put(PlayerAttributes.SKILL_FK_ACCURACY, 80);
		skills.put(PlayerAttributes.SKILL_LONG_PASSING, 80);
		skills.put(PlayerAttributes.SKILL_BALL_CONTROL, 80);
		skills.put(PlayerAttributes.MOVEMENT_ACCELERATION, 80);
		skills.put(PlayerAttributes.MOVEMENT_SPRINT_SPEED, 80);
		skills.put(PlayerAttributes.MOVEMENT_REACTIONS, 80);
		skills.put(PlayerAttributes.MOVEMENT_AGILITY, 80);
		skills.put(PlayerAttributes.MOVEMENT_BALANCE, 80);
		skills.put(PlayerAttributes.POWER_SHOT_POWER, 80);
		skills.put(PlayerAttributes.POWER_JUMPING, 80);
		skills.put(PlayerAttributes.POWER_STAMINA, 80);
		skills.put(PlayerAttributes.POWER_STRENGTH, 80);
		skills.put(PlayerAttributes.POWER_LONG_SHOTS, 80);
		skills.put(PlayerAttributes.MENTALITY_AGGRESSION, 80);
		skills.put(PlayerAttributes.MENTALITY_INTERCEPTIONS, 80);
		skills.put(PlayerAttributes.MENTALITY_POSITIONING, 80);
		skills.put(PlayerAttributes.MENTALITY_VISION, 80);
		skills.put(PlayerAttributes.MENTALITY_PENALTIES, 80);
		skills.put(PlayerAttributes.MENTALITY_COMPOSURE, 80);
		skills.put(PlayerAttributes.DEFENDING_MARKING_AWARENESS, 80);
		skills.put(PlayerAttributes.DEFENDING_STANDING_TACKLE, 80);
		skills.put(PlayerAttributes.DEFENDING_SLIDING_TACKLE, 80);
		skills.put(PlayerAttributes.GOALKEEPING_DIVING, 80);
		skills.put(PlayerAttributes.GOALKEEPING_HANDLING, 80);
		skills.put(PlayerAttributes.GOALKEEPING_KICKING, 80);
		skills.put(PlayerAttributes.GOALKEEPING_POSITIONING, 80);
		skills.put(PlayerAttributes.GOALKEEPING_REFLEXES, 80);
		skills.put(PlayerAttributes.GOALKEEPING_SPEED, 80);
	}


	@Test
	void getPlayerIdTest() {
		int playerId = player.getPlayerId();
		assertEquals(1,playerId);
	}

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

	@Test
	void isAvailable() {
		Boolean availability = player.isAvailable();
		assertEquals(true,availability);
	}
}