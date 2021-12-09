package com.gameplay.service;

import com.models.ClubModel;
import com.models.PlayerModel;
import java.util.HashMap;

public interface IGameService {
	HashMap<PlayerModel, Integer> getHighestGoalScorer();

	HashMap<ClubModel, Integer> getHighestGoalsByClub();

	void resetPlayerGoals();

	void resetClubGoals();
}
