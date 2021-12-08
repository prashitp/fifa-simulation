package com.gameplay.scoreline.GoalScorer;

import com.Constants;
import com.gameplay.service.GoalScorerService;
import com.gameplay.service.IGoalScorerService;
import com.models.PlayerModel;
import java.util.List;
/**
 * @author prashitpatel
 */
public class OpenPlayScorer implements Scorer{
	IGoalScorerService goalScorerService = new GoalScorerService();

	@Override
	public PlayerModel getScorer(List<PlayerModel> players) {
		return goalScorerService.getScorerForType(Constants.ATTACKING_SKILLS,
				Constants.ATTACKING_POSITIONS, players);
	}
}
