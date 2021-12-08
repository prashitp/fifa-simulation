package com.gameplay.service;

import com.models.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author prashitpatel
 */
public class GoalScorerService implements IGoalScorerService {
	public PlayerModel getPlayerForPenalty(List<PlayerModel> players) {
		Collections.sort(players, new Comparator<PlayerModel>() {
			@Override
			public int compare(PlayerModel o1, PlayerModel o2) {
				int result = o2.overall - o1.overall;
				if(result == 0) {
					result = o2.skills.get(PlayerAttributes.MENTALITY_PENALTIES) - o1.skills.get(PlayerAttributes.MENTALITY_PENALTIES);
				}
				return  result;
			}
		});

		return players.get(0);
	}

	public PlayerModel getScorerForType(PlayerAttributes[] playerAttributes, PlayerPositions[] positions,
										List<PlayerModel> players) {
		HashMap<PlayerModel, Integer> possibleScorers = new HashMap<>();

		for (PlayerModel player: players) {
			int skillAverage = 0;
			PlayerPositions[] matchingPositions = Arrays.stream(player.getPositions()).filter(position ->
					Arrays.asList(positions).contains(position)).toArray(PlayerPositions[]::new);
			if(matchingPositions.length > 0) {
				skillAverage = countSkills(player, playerAttributes);
				possibleScorers.put(player, skillAverage);
			}
		}

		int selectedIndex = generateRandom(0,possibleScorers.size()-1);
		return possibleScorers.keySet().stream().collect(Collectors.toList()).get(selectedIndex);
	}

	private int countSkills(PlayerModel player, PlayerAttributes[] attributes) {
		int aggregate = 0;
		for (PlayerAttributes attribute: attributes) {
			aggregate += player.skills.get(attribute)/attributes.length;
		}
		return aggregate;
	}

	private int generateRandom(int lowerBound, int upperBound) {
		Random random = new Random();
		return (int) Math.round(random.nextDouble() * (upperBound - lowerBound) + lowerBound);
	}
}