package com.gameplay.TeamSelection.FormationSelection;

import com.utils.Constants;
import com.models.FormationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class ClubRelativeStrengthCriteriaTest {
	ClubRelativeStrengthCriteria clubRelativeStrengthCriteria = new ClubRelativeStrengthCriteria();

	@Test
	void getResults() {
		FormationType formationType = clubRelativeStrengthCriteria.getResults(Constants.CLUBS[0], Constants.CLUBS[1]);
		assertEquals("ATTACKING",formationType.toString());
	}

}