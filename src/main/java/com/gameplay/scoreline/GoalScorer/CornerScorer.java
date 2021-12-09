package com.gameplay.scoreline.GoalScorer;

import com.utils.Constants;
import com.gameplay.service.GoalScorerService;
import com.gameplay.service.IGoalScorerService;
import com.models.PlayerModel;

import java.util.List;

/**
 * @author prashitpatel
 */
public class CornerScorer implements Scorer{
	IGoalScorerService goalScorerService = new GoalScorerService();

	@Override
	public PlayerModel getScorer(List<PlayerModel> players) {
		return goalScorerService.getScorerForType(Constants.CORNER_SKILLS,
				Constants.DEFENDING_POSITIONS, players);
	}
}
