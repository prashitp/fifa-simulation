package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author prashitpatel
 */
class GoalkeeperCategoryTest {
	IPlayerCategory goalkeepers = new GoalkeeperCategory(Arrays.asList(Constants.PLAYERS[8]));

	@Test
	void notNullTest() {
		assertNotNull(goalkeepers);
	}
}