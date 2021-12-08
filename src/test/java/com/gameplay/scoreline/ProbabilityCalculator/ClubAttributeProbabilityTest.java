package com.gameplay.scoreline.ProbabilityCalculator;

import com.Constants;
import com.models.ClubModel;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class ClubAttributeProbabilityTest {
	ClubAttributeProbability clubAttributeProbability = new ClubAttributeProbability(Constants.CLUBS[0], Constants.CLUBS[1]);

	@Test
	void getProbabilityTest() {
		HashMap<ClubModel,Double> probability = clubAttributeProbability.getProbability();
		assertEquals(2,probability.size());
	}

}