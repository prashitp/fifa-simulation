package com.gameplay.scoreline.GoalScorer;

import com.utils.Constants;
import com.gameplay.service.GoalScorerService;
import com.gameplay.service.IGoalScorerService;
import com.models.PlayerModel;

import java.util.List;
/**
 * @author prashitpatel
 */
public class FreeKickScorer implements Scorer{
	IGoalScorerService goalScorerService = new GoalScorerService();

	@Override
	public PlayerModel getScorer(List<PlayerModel> players) {
		PlayerModel player = goalScorerService.getScorerForType(Constants.FREE_KICK_SKILLS,
				Constants.ATTACKING_POSITIONS, players);
		player.goals += 1;
		return player;
	}
}
