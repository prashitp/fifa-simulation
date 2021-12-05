package com.gameplay.TeamSelection.FormationSelection;

import com.Constants;
import com.models.gameplay.TeamSelection.FormationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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