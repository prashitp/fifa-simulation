package com.utils;

import com.gameplay.entity.PlayerEntity;
import com.models.ClubAttributes;
import com.models.FormationType;
import com.models.PlayerPositions;

import java.util.*;
/**
 * @author prashitpatel and Mayank Sareen
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
	
	/**
	 * @author Mayank Sareen
	 */
	public static Double generateRandomDoubleBetweenRange(int min, int max) {
		return  min + (Math.random() * (max - min));
	}

	public static Integer generateRandomIntegerBetweenRange(int min, int max) {
		return(randomInteger(min,max));
	}

	public static Double generateRandomDouble() {
		Random r = new Random();
		return r.nextDouble();
	}

	public static Integer generateRandomInteger() {
		Random r = new Random();
		return Integer.valueOf(r.nextInt());
	}

	private static Integer randomInteger(int min, int max) {
		return  min + (int)(Math.random() * (max - min));
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
