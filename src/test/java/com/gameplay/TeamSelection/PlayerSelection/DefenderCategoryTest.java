package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author prashitpatel
 */
class DefenderCategoryTest {
	IPlayerCategory defenders = new DefenderCategory(Arrays.asList(Constants.PLAYERS[5]));
	@Test
	void notNullTest() {
		assertNotNull(defenders);
	}
}