package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author prashitpatel
 */
class MidfielderCategoryTest {
	IPlayerCategory midfielders = new MidfielderCategory(Arrays.asList(Constants.PLAYERS[1]));
	@Test
	void notNullTest() {
		assertNotNull(midfielders);
	}
}