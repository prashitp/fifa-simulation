package com.gameplay.scoreline.ProbabilityCalculator;

import com.utils.Constants;
import com.models.ClubModel;
import com.models.FormationModel;
import com.models.FormationType;

import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class FormationCriteriaProbability extends ProbabilityCriteria {
	FormationModel homeClubFormation;
	FormationModel awayClubFormation;

	public FormationCriteriaProbability(ClubModel homeClub, ClubModel awayClub, FormationModel homeClubFormation, FormationModel awayClubFormation) {
		super(homeClub, awayClub);
		this.homeClubFormation = homeClubFormation;
		this.awayClubFormation = awayClubFormation;
	}

	public HashMap<ClubModel, Double> getProbability() {
		List<Integer> homeFormation = homeClubFormation.getFormation();
		List<Integer> awayFormation = awayClubFormation.getFormation();

		boolean homeExtendedFormation = homeFormation.size() == 4 ? true : false;
		boolean awayExtendedFormation = awayFormation.size() == 4 ? true : false;

		int homeDefenders = homeFormation.get(0);
		int homeForwards = homeExtendedFormation ? homeFormation.get(3) : homeFormation.get(2);

		int awayDefenders = awayFormation.get(0);
		int awayForwards = awayExtendedFormation ? awayFormation.get(3) : awayFormation.get(2);

		double homeProbability;
		double awayProbability;

		if (homeExtendedFormation && !awayExtendedFormation) {
			homeForwards = homeForwards+homeFormation.get(2);
			homeDefenders = homeDefenders+homeFormation.get(1);
		} else if (!homeExtendedFormation && awayExtendedFormation) {
			awayForwards = awayForwards+awayFormation.get(2);
			awayDefenders = awayDefenders+awayFormation.get(1);
		} else if (homeExtendedFormation && awayExtendedFormation) {
			homeForwards = homeForwards+homeFormation.get(2);
			homeDefenders = homeDefenders+homeFormation.get(1);
			awayForwards = awayForwards+awayFormation.get(2);
			awayDefenders = awayDefenders+awayFormation.get(1);
		}

		homeProbability = Constants.STARTING_PROBABILITY + getTypeProbability(homeClubFormation.type) + ((homeForwards - awayDefenders)*Constants.STARTING_PROBABILITY)/10;
		awayProbability = Constants.STARTING_PROBABILITY + getTypeProbability(awayClubFormation.type) + ((awayForwards - homeDefenders)*Constants.STARTING_PROBABILITY)/10;

		HashMap<ClubModel, Double> formationProbability = new HashMap<>();
		formationProbability.put(homeClub, homeProbability);
		formationProbability.put(awayClub, awayProbability);

		return formationProbability;
	}

	private double getTypeProbability(FormationType formationType) {
		if (formationType.equals(FormationType.ATTACKING)) {
			return 0.1;
		} else if (formationType.equals(FormationType.DEFENSIVE)) {
			return -0.1;
		} else {
			return 0;
		}
	}
}

