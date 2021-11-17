//Author - Prashit Patel
package com.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubModelTest {

	ClubModel club = new ClubModel(1,"Arsenal",79,83,79,77,80000000);

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