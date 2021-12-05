package com.gameplay.ScoreLine.GoalCalculator;

import com.Constants;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author prashitpatel
 */
class ClubAttributeProbabilityTest {
	ClubAttributeProbability clubAttributeProbability = new ClubAttributeProbability(Constants.CLUBS[0], Constants.CLUBS[1]);

	@Test
	void getProbabilityTest() {
		List<Double> probability = clubAttributeProbability.getProbability();
		assertEquals(2,probability.size());
	}

}