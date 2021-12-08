package com.gameplay.scoreline.ProbabilityCalculator;

import com.Constants;
import com.models.ClubAttributes;
import com.models.ClubModel;
import java.util.HashMap;
/**
 * @author prashitpatel
 */
public class ClubAttributeProbability extends ProbabilityCriteria {
	public ClubAttributeProbability(ClubModel homeClub, ClubModel awayClub) {
		super(homeClub, awayClub);
	}

	public HashMap<ClubModel, Double> getProbability() {
		int homeAttack = (homeClub.attributes.get(ClubAttributes.ATTACK) - awayClub.attributes.get(ClubAttributes.DEFENCE));
		int awayAttack = (awayClub.attributes.get(ClubAttributes.ATTACK) - homeClub.attributes.get(ClubAttributes.DEFENCE));

		double homeProbability = Constants.STARTING_PROBABILITY + (homeAttack * Constants.STARTING_PROBABILITY)/10;
		double awayProbability = Constants.STARTING_PROBABILITY + (awayAttack * Constants.STARTING_PROBABILITY)/10;

		HashMap<ClubModel,Double> clubAttributeProbability = new HashMap<>();
		clubAttributeProbability.put(homeClub, homeProbability);
		clubAttributeProbability.put(awayClub, awayProbability);

		return clubAttributeProbability;
	}
}
