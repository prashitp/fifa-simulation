package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
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