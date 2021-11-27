package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
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