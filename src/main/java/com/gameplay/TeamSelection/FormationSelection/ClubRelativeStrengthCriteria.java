package com.gameplay.TeamSelection.FormationSelection;

import com.models.ClubAttributes;
import com.models.ClubModel;
import com.models.gameplay.TeamSelection.FormationType;
import com.CommonFunctions;
import java.util.HashMap;
import java.util.Map;
/**
 * @author prashitpatel
 */
public class ClubRelativeStrengthCriteria implements IFormationCriteriaCategory{
	public FormationType getResults(ClubModel club, ClubModel opposingClub) {
		HashMap<ClubAttributes,Integer> attributeDiff = new HashMap<>();
		attributeDiff.put(ClubAttributes.Attack,Math.abs(club.attributes.get(ClubAttributes.Attack) - opposingClub.attributes.get(ClubAttributes.Attack)));
		attributeDiff.put(ClubAttributes.Midfield,Math.abs(club.attributes.get(ClubAttributes.Midfield) - opposingClub.attributes.get(ClubAttributes.Midfield)));
		attributeDiff.put(ClubAttributes.Defence,Math.abs(club.attributes.get(ClubAttributes.Defence) - opposingClub.attributes.get(ClubAttributes.Defence)));

		Map.Entry<ClubAttributes,Integer> maxAttribute = CommonFunctions.maxClubAttribute(attributeDiff);
		return CommonFunctions.mapClubAttributeToFormationType(maxAttribute.getKey());
	}
}
