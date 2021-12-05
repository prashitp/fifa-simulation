package com.gameplay.ScoreLine.GoalCalculator;

import com.Constants;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import java.util.ArrayList;
import java.util.List;
/**
 * @author prashitpatel
 */
public class FormationCriteriaProbability implements IProbabilityCalculator {
	FormationModel homeClubFormation;
	FormationModel awayClubFormation;

	public FormationCriteriaProbability(FormationModel homeClubFormation, FormationModel awayClubFormation) {
		this.homeClubFormation = homeClubFormation;
		this.awayClubFormation = awayClubFormation;
	}

	public List<Double> getProbability() {
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

		List<Double> formationProbability = new ArrayList<>();
		formationProbability.add(homeProbability);
		formationProbability.add(awayProbability);

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

