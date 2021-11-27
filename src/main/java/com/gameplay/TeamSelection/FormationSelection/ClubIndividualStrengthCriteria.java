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
public class ClubIndividualStrengthCriteria implements IFormationCriteriaCategory{
	public FormationType getResults(ClubModel club, ClubModel opposingClub) {
		HashMap<ClubAttributes,Integer> attributes = new HashMap<>();
		attributes.put(ClubAttributes.Attack, club.attributes.get(ClubAttributes.Attack));
		attributes.put(ClubAttributes.Midfield, club.attributes.get(ClubAttributes.Midfield));
		attributes.put(ClubAttributes.Defence, club.attributes.get(ClubAttributes.Defence));
		Map.Entry<ClubAttributes,Integer> maxAttribute = CommonFunctions.maxClubAttribute(attributes);
		return CommonFunctions.mapClubAttributeToFormationType(maxAttribute.getKey());
	}
}
