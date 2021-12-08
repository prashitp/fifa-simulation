package com.gameplay.service;

import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayerPositions;
import java.util.List;
/**
 * @author prashitpatel
 */
public interface IGoalScorerService {
	PlayerModel getPlayerForPenalty(List<PlayerModel> players);

	PlayerModel getScorerForType(PlayerAttributes[] playerAttributes, PlayerPositions[] positions,
								 List<PlayerModel> players);
}
