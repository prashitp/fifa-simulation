package com;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.PlayerEntity;
import com.models.ClubAttributes;
import com.models.PlayerPositions;
import com.models.gameplay.TeamSelection.FormationType;
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
		
	public static String convertIntegerToString(Integer i) {
		return i.toString();
	}

    public static List<PlayerPositions> fetchPlayerPositions(PlayerEntity player) {
        List<PlayerPositions> playerPositions = new ArrayList<>(List.of());
        String[] positions = player.getPlayerPositions().split("(, )");
        for (String position : positions) {
        	playerPositions.add(PlayerPositions.valueOf(PlayerPositions.class, position));
        }
        return playerPositions;
    }
}
