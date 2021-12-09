package com.gameplay.scoreline.GoalScorer;

import com.models.PlayerModel;

import java.util.List;
/**
 * @author prashitpatel
 */
public interface Scorer {
	PlayerModel getScorer(List<PlayerModel> players);
}
