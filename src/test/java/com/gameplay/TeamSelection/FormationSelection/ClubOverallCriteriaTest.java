package com.gameplay.TeamSelection.FormationSelection;

import com.utils.Constants;
import com.models.FormationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class ClubOverallCriteriaTest {
	ClubOverallCriteria clubOverallCriteria = new ClubOverallCriteria();

	@Test
	void getResultsTest() {
		FormationType formationType = clubOverallCriteria.getResults(Constants.CLUBS[1], Constants.CLUBS[0]);
		assertEquals("NEUTRAL",formationType.toString());
	}

}