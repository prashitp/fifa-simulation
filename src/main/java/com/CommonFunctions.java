package com;

import com.models.ClubAttributes;
import com.models.gameplay.TeamSelection.FormationType;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
/**
 * @author prashitpatel
 */
public class CommonFunctions {
	public static HashMap.Entry<ClubAttributes, Integer> maxClubAttribute(HashMap<ClubAttributes, Integer> attributeSet){
		Map.Entry<ClubAttributes, Integer> maxEntry = null;
		for (ClubAttributes key: attributeSet.keySet()) {
			if (maxEntry == null || attributeSet.get(key).compareTo(maxEntry.getValue()) > 0)
			{
				maxEntry = new AbstractMap.SimpleEntry<ClubAttributes,Integer>(key,attributeSet.get(key));
			}
		}
		return maxEntry;
	};

	public static FormationType mapClubAttributeToFormationType(ClubAttributes key) {
		FormationType type = FormationType.NEUTRAL;
		if(key.toString().equals("ATTACK")) {
			type = FormationType.ATTACKING;
		} else if(key.toString().equals("MIDFIELD")) {
			type = FormationType.NEUTRAL;
		} else if(key.toString().equals(("DEFENCE"))){
			type = FormationType.DEFENSIVE;
		}
		return type;
	}
}
