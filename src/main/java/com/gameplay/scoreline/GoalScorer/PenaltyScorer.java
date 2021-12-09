package com.gameplay.scoreline.GoalScorer;

import com.gameplay.service.GoalScorerService;
import com.gameplay.service.IGoalScorerService;
import com.models.PlayerModel;

import java.util.List;
/**
 * @author prashitpatel
 */
public class PenaltyScorer implements Scorer {
	IGoalScorerService goalScorerService = new GoalScorerService();

	public PlayerModel getScorer(List<PlayerModel> players) {
		PlayerModel player = goalScorerService.getPlayerForPenalty(players);
		player.goals += 1;
		return player;
	}
}
