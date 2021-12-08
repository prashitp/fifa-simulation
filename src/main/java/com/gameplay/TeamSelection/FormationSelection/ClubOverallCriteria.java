package com.gameplay.TeamSelection.FormationSelection;

import com.models.ClubAttributes;
import com.models.ClubModel;
import com.models.FormationType;
/**
 * @author prashitpatel
 */
public class ClubOverallCriteria implements IFormationCriteriaCategory{
	public FormationType getResults(ClubModel club, ClubModel opposingClub) {
		FormationType result;
		int overallDifference = club.attributes.get(ClubAttributes.OVERALL) - opposingClub.attributes.get(ClubAttributes.OVERALL);
		if(overallDifference < 0) {
			result = overallDifference < -4 ? FormationType.DEFENSIVE : FormationType.NEUTRAL;
		} else {
			result = overallDifference > 4 ? FormationType.ATTACKING : FormationType.NEUTRAL;
		}
		return result;
	}
}
