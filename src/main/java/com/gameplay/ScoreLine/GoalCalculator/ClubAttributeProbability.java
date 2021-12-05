package com.gameplay.ScoreLine.GoalCalculator;

import com.Constants;
import com.models.ClubAttributes;
import com.models.ClubModel;
import java.util.ArrayList;
import java.util.List;
/**
 * @author prashitpatel
 */
public class ClubAttributeProbability implements IProbabilityCalculator {
	ClubModel homeClub;
	ClubModel awayClub;

	public ClubAttributeProbability(ClubModel homeClub, ClubModel awayClub) {
		this.homeClub = homeClub;
		this.awayClub = awayClub;
	}

	public List<Double> getProbability() {
		int homeAttack = (homeClub.attributes.get(ClubAttributes.ATTACK) - awayClub.attributes.get(ClubAttributes.DEFENCE));
		int awayAttack = (awayClub.attributes.get(ClubAttributes.ATTACK) - homeClub.attributes.get(ClubAttributes.DEFENCE));

		double homeProbability = Constants.STARTING_PROBABILITY + (homeAttack * Constants.STARTING_PROBABILITY)/10;
		double awayProbability = Constants.STARTING_PROBABILITY + (awayAttack * Constants.STARTING_PROBABILITY)/10;

		List<Double> clubAttributeProbability = new ArrayList<>();
		clubAttributeProbability.add(homeProbability);
		clubAttributeProbability.add(awayProbability);

		return clubAttributeProbability;
	}
}
