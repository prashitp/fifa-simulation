package com.models;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class ClubModelTest {
	static HashMap<ClubAttributes,Integer> attributes;
	ClubModel club = new ClubModel(1,"Arsenal",attributes,80000000);
	@BeforeAll
	static void initialize() {
		System.out.println("Before called");
		attributes = new HashMap<>();
		attributes.put(ClubAttributes.Overall,79);
		attributes.put(ClubAttributes.Attack,79);
		attributes.put(ClubAttributes.Midfield,79);
		attributes.put(ClubAttributes.Defence,79);
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
}