package com.gameplay.TeamSelection.FormationSelection;

import com.models.ClubAttributes;
import com.models.ClubModel;
import com.models.gameplay.TeamSelection.FormationType;
/**
 * @author prashitpatel
 */
public class ClubOverallCriteria implements IFormationCriteriaCategory{
	public FormationType getResults(ClubModel club, ClubModel opposingClub) {
		FormationType result;
		int overallDifference = club.attributes.get(ClubAttributes.Overall) - opposingClub.attributes.get(ClubAttributes.Overall);
		if(overallDifference < 0) {
			result = overallDifference < -4 ? FormationType.Defensive : FormationType.Neutral;
		} else {
			result = overallDifference > 4 ? FormationType.Attacking : FormationType.Neutral;
		}
		return result;
	}
}
