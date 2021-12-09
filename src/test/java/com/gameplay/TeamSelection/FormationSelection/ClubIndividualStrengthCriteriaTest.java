package com.gameplay.TeamSelection.FormationSelection;

import com.utils.Constants;
import com.models.FormationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author prashitpatel
 */
class ClubIndividualStrengthCriteriaTest {
	ClubIndividualStrengthCriteria clubIndividualStrengthCriteria = new ClubIndividualStrengthCriteria();

	@Test
	void getResultsTest() {
		FormationType formationType= clubIndividualStrengthCriteria.getResults(Constants.CLUBS[0],Constants.CLUBS[1] );
		assertEquals("ATTACKING",formationType.toString());
	}

}