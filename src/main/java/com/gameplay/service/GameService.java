package com.gameplay.service;

import com.models.ClubModel;
import com.models.PlayerModel;
import com.utils.Constants;

import java.util.HashMap;

/**
 * @author prashitpatel
 */
public class GameService implements IGameService {
	public HashMap<PlayerModel, Integer> getHighestGoalScorer() {
		int max = 0;
		PlayerModel scorer = Constants.PLAYERS[0];
		for (PlayerModel player: Constants.PLAYERS) {
			if(player.goals > max) {
				max = player.goals;
				scorer = player;
			}
		}
		HashMap<PlayerModel, Integer> highestScorer = new HashMap<>();
		highestScorer.put(scorer, max);
		return highestScorer;
	}

	public HashMap<ClubModel, Integer> getHighestGoalsByClub() {
		int max = 0;
		ClubModel scorerClub = Constants.CLUBS[0];
		for (ClubModel club: Constants.CLUBS) {
			if(club.goals > max) {
				max = club.goals;
				scorerClub = club;
			}
		}
		HashMap<ClubModel, Integer> highestScorer = new HashMap<>();
		highestScorer.put(scorerClub, max);
		return highestScorer;
	}

	public void resetPlayerGoals(){
		for (PlayerModel player: Constants.PLAYERS) {
			player.goals = 0;
		}
	}

	public void resetClubGoals(){
		for (ClubModel club: Constants.CLUBS) {
			club.goals = 0;
		}
	}
}
