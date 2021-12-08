package com.gameplay.scoreline.ProbabilityCalculator;

import com.Constants;
import com.models.ClubModel;
import com.models.FormationModel;
import com.models.FormationType;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class FormationCriteriaProbabilityTest {
	FormationModel homeFormation = new FormationModel(4,4,2, FormationType.NEUTRAL);
	FormationModel awayFormation = new FormationModel(4,4,2, FormationType.NEUTRAL);

	FormationCriteriaProbability formationCriteriaProbability = new FormationCriteriaProbability(Constants.CLUBS[0],
			Constants.CLUBS[1], homeFormation,awayFormation);

	@Test
	void getProbabilityTest() {
		HashMap<ClubModel,Double> probability = formationCriteriaProbability.getProbability();
		assertEquals(2,probability.size());
	}
}