package com.gameplay.ScoreLine.GoalCalculator;

import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class FormationCriteriaProbabilityTest {
	FormationModel homeFormation = new FormationModel(4,4,2, FormationType.Neutral);
	FormationModel awayFormation = new FormationModel(4,4,2, FormationType.Neutral);
	FormationCriteriaProbability formationCriteriaProbability = new FormationCriteriaProbability(homeFormation,awayFormation);

	@Test
	void getProbabilityTest() {
		List<Double> probability = formationCriteriaProbability.getProbability();
		assertEquals(2,probability.size());
	}
}