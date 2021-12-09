package com.gameplay.controller;

import com.gameplay.scoreline.GoalScorer.Scorer;
import com.gameplay.scoreline.GoalScorer.ScorerFactory;
import com.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author prashitpatel
 */
public class GoalScorerController implements IGoalScorerController {
	private static HashMap<ClubModel, List<Goal>> goalScorers;
	List<Lineup> lineups;

	public GoalScorerController(List<Lineup> lineups) {
		this.lineups = lineups;
	}

	public void calculateGoalScorer(ClubModel club, GoalType goalType, ClubModel awayClub) {
		ScorerFactory scorerFactory = new ScorerFactory();
		Scorer scorer = scorerFactory.getScorerType(goalType);
		Lineup clubLineup;
		if(goalType.equals(GoalType.OWN_GOAL)) {
			clubLineup = lineups.stream().filter(lineup -> lineup.club.equals(awayClub)).collect(Collectors.toList()).get(0);
		} else {
			clubLineup = lineups.stream().filter(lineup -> lineup.club.equals(club)).collect(Collectors.toList()).get(0);
		}

		HashMap<PlayerModel, PlayingPosition> clubPlaying11 = clubLineup.getPlaying11();
		List<PlayerModel> players = new ArrayList<>(clubPlaying11.keySet());

		Goal goal = new Goal(goalType, scorer.getScorer(players));

		if(goalScorers.containsKey(club)) {
			goalScorers.get(club).add(goal);
		} else {
			List<Goal> goalList = new ArrayList<>();
			goalList.add(goal);
			goalScorers.put(club, goalList);
		}
	}

	public static HashMap<ClubModel, List<Goal>> getGoalScorers() {
		return goalScorers;
	}

	public static void resetGoalScorers() {
		goalScorers = new HashMap<>();
	}
}
