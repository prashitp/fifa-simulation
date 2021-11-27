package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
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