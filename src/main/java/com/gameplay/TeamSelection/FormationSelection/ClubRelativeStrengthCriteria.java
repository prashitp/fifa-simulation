package com.gameplay.TeamSelection.FormationSelection;

import com.utils.CommonFunctions;
import com.models.ClubAttributes;
import com.models.ClubModel;
import com.models.FormationType;

import java.util.HashMap;
import java.util.Map;
/**
 * @author prashitpatel
 */
public class ClubRelativeStrengthCriteria implements IFormationCriteriaCategory{
	public FormationType getResults(ClubModel club, ClubModel opposingClub) {
		HashMap<ClubAttributes,Integer> attributeDiff = new HashMap<>();
		attributeDiff.put(ClubAttributes.ATTACK,Math.abs(club.attributes.get(ClubAttributes.ATTACK) - opposingClub.attributes.get(ClubAttributes.ATTACK)));
		attributeDiff.put(ClubAttributes.MIDFIELD,Math.abs(club.attributes.get(ClubAttributes.MIDFIELD) - opposingClub.attributes.get(ClubAttributes.MIDFIELD)));
		attributeDiff.put(ClubAttributes.DEFENCE,Math.abs(club.attributes.get(ClubAttributes.DEFENCE) - opposingClub.attributes.get(ClubAttributes.DEFENCE)));

		Map.Entry<ClubAttributes,Integer> maxAttribute = CommonFunctions.maxClubAttribute(attributeDiff);
		return CommonFunctions.mapClubAttributeToFormationType(maxAttribute.getKey());
	}
}
