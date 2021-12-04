package com;

import com.models.ClubAttributes;
import com.models.gameplay.TeamSelection.FormationType;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
		FormationType type = FormationType.Neutral;
		if(key.toString().equals("Attack")) {
			type = FormationType.Attacking;
		} else if(key.toString().equals("Midfield")) {
			type = FormationType.Neutral;
		} else if(key.toString().equals(("Defence"))){
			type = FormationType.Defensive;
		}
		return type;
	}
	/**
	 * @author Mayank Sareen
	 */
	public static Double generateRandomDoubleBetweenRange(int min, int max) {
		return  min + (Math.random() * ((max - min) + 1));
	}

	public static Integer generateRandomIntegerBetweenRange(int min, int max) {
		return(randomFactor(min,max));
	}

	public static Double generateRandomDouble() {
		Random r = new Random();
		return r.nextDouble();
	}

	public static Integer generateRandomInteger() {
		Random r = new Random();
		return Integer.valueOf(r.nextInt());
	}

	private static Integer randomFactor(int min, int max) {
		return  min + (int)(Math.random() * ((max - min) + 1));

	}
}
