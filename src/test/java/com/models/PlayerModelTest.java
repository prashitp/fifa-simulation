package com.models;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class PlayerModelTest {
	static HashMap<PlayerAttributes,Integer> skills;
	PlayerModel player= new PlayerModel(1,
			"Cristiano Ronaldo",
			new PlayerPositions[]{PlayerPositions.ST,PlayerPositions.LW},
			91,91,45000000,36,187,83,"Manchester United",
			PlayerPositions.ST,7,23,"Portugal","Right",5,
			PlayerWorkRate.HIGH, new String[]{"Power Free-Kick", "Flair", "Long Shot Taker (AI)", "Speed Dribbler (AI)", "Outside Foot Shot"},
			skills
	);

	@BeforeAll
	static void initialize() {
		System.out.println("Before called");
		skills = new HashMap<>();
		skills.put(PlayerAttributes.Pace, 80);
		skills.put(PlayerAttributes.Shooting, 80);
		skills.put(PlayerAttributes.Passing, 80);
		skills.put(PlayerAttributes.Dribbling, 80);
		skills.put(PlayerAttributes.Defending, 80);
		skills.put(PlayerAttributes.Physic, 80);
		skills.put(PlayerAttributes.AttackingCrossing, 80);
		skills.put(PlayerAttributes.AttackingFinishing, 80);
		skills.put(PlayerAttributes.AttackingHeadingAccuracy, 80);
		skills.put(PlayerAttributes.AttackingShortPassing, 80);
		skills.put(PlayerAttributes.AttackingVolleys, 80);
		skills.put(PlayerAttributes.SkillDribbling, 80);
		skills.put(PlayerAttributes.SkillCurve, 80);
		skills.put(PlayerAttributes.SkillFkAccuracy, 80);
		skills.put(PlayerAttributes.SkillLongPassing, 80);
		skills.put(PlayerAttributes.SkillBallControl, 80);
		skills.put(PlayerAttributes.MovementAcceleration, 80);
		skills.put(PlayerAttributes.MovementSprintSpeed, 80);
		skills.put(PlayerAttributes.MovementReactions, 80);
		skills.put(PlayerAttributes.MovementAgility, 80);
		skills.put(PlayerAttributes.MovementBalance, 80);
		skills.put(PlayerAttributes.PowerShotPower, 80);
		skills.put(PlayerAttributes.PowerJumping, 80);
		skills.put(PlayerAttributes.PowerStamina, 80);
		skills.put(PlayerAttributes.PowerStrength, 80);
		skills.put(PlayerAttributes.PowerLongShots, 80);
		skills.put(PlayerAttributes.MentalityAggression, 80);
		skills.put(PlayerAttributes.MentalityInterceptions, 80);
		skills.put(PlayerAttributes.MentalityPositioning, 80);
		skills.put(PlayerAttributes.MentalityVision, 80);
		skills.put(PlayerAttributes.MentalityPenalties, 80);
		skills.put(PlayerAttributes.MentalityComposure, 80);
		skills.put(PlayerAttributes.DefendingMarkingAwareness, 80);
		skills.put(PlayerAttributes.DefendingStandingTackle, 80);
		skills.put(PlayerAttributes.DefendingSlidingTackle, 80);
		skills.put(PlayerAttributes.GoalkeepingDiving, 80);
		skills.put(PlayerAttributes.GoalkeepingHandling, 80);
		skills.put(PlayerAttributes.GoalkeepingKicking, 80);
		skills.put(PlayerAttributes.GoalkeepingPositioning, 80);
		skills.put(PlayerAttributes.GoalkeepingReflexes, 80);
		skills.put(PlayerAttributes.GoalkeepingSpeed, 80);
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
}