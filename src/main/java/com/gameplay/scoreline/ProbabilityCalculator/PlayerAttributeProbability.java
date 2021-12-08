package com.gameplay.scoreline.ProbabilityCalculator;

import com.Constants;
import com.models.ClubModel;
import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class PlayerAttributeProbability extends ProbabilityCriteria {
	HashMap<PlayerModel, PlayingPosition> homePlaying11;
	HashMap<PlayerModel, PlayingPosition> awayPlaying11;

	public PlayerAttributeProbability(ClubModel homeClub, ClubModel awayClub, HashMap<PlayerModel, PlayingPosition> homePlaying11, HashMap<PlayerModel, PlayingPosition> awayPlaying11) {
		super(homeClub,awayClub);
		this.homePlaying11 = homePlaying11;
		this.awayPlaying11 = awayPlaying11;
	}

	public HashMap<ClubModel,Double> getProbability() {
		List<Integer> homeAverage = getAverage(homePlaying11);
		List<Integer> awayAverage = getAverage(awayPlaying11);

		int homeAttackAverage = homeAverage.get(0);
		int homeDefenceAverage =homeAverage.get(1);
		int awayAttackAverage = awayAverage.get(0);
		int awayDefenceAverage = awayAverage.get(1);

		double homeProbability = Constants.STARTING_PROBABILITY + ((homeAttackAverage - awayDefenceAverage) * Constants.STARTING_PROBABILITY)/10;
		double awayProbability = Constants.STARTING_PROBABILITY + ((awayAttackAverage - homeDefenceAverage) * Constants.STARTING_PROBABILITY)/10;

		HashMap<ClubModel, Double> playerAttributeProbability = new HashMap<>();
		playerAttributeProbability.put(homeClub, homeProbability);
		playerAttributeProbability.put(awayClub, awayProbability);
		return playerAttributeProbability;
	}

	private List<Integer> getAverage(HashMap<PlayerModel, PlayingPosition> playing11) {
		int attackingAverage = 0;
		int attackerCount = 0;
		int defendingAverage = 0;
		int defenderCount = 0;
		List<Integer> teamAverage = new ArrayList<>();

		for (PlayerModel player: playing11.keySet()) {
			if(playing11.get(player).equals(PlayingPosition.FORWARD)) {
				attackerCount++;
				attackingAverage += countSkills(player, Constants.ATTACKING_SKILLS);
			} else if(playing11.get(player).equals(PlayingPosition.MIDFIELDER)) {
				attackerCount++;
				attackingAverage += countSkills(player, Constants.MIDFIELD_SKILLS);
			} else if(playing11.get(player).equals(PlayingPosition.DEFENDER)) {
				defenderCount++;
				defendingAverage += countSkills(player, Constants.DEFENDING_SKILLS);
			} else {
				defenderCount++;
				defendingAverage += countSkills(player, Constants.GOALKEEPING_SKILLS);
			}
		}
		teamAverage.add(attackingAverage/attackerCount);
		teamAverage.add(defendingAverage/defenderCount);
		return teamAverage;
	}

	private int countSkills(PlayerModel player, PlayerAttributes[] attributes) {
		int aggregate = 0;
		for (PlayerAttributes attribute: attributes) {
			aggregate += player.skills.get(attribute)/attributes.length;
		}
		return aggregate;
	}
}



