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
public class ClubIndividualStrengthCriteria implements IFormationCriteriaCategory{
	public FormationType getResults(ClubModel club, ClubModel opposingClub) {
		HashMap<ClubAttributes,Integer> attributes = new HashMap<>();
		attributes.put(ClubAttributes.ATTACK, club.attributes.get(ClubAttributes.ATTACK));
		attributes.put(ClubAttributes.MIDFIELD, club.attributes.get(ClubAttributes.MIDFIELD));
		attributes.put(ClubAttributes.DEFENCE, club.attributes.get(ClubAttributes.DEFENCE));
		Map.Entry<ClubAttributes,Integer> maxAttribute = CommonFunctions.maxClubAttribute(attributes);
		return CommonFunctions.mapClubAttributeToFormationType(maxAttribute.getKey());
	}
}
