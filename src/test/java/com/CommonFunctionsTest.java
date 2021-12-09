package com;

import com.models.ClubAttributes;
import com.models.FormationType;
import com.utils.CommonFunctions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class CommonFunctionsTest {
	@Test
	void maxClubAttributeTest() {
		HashMap<ClubAttributes,Integer> input = new HashMap<>();
		input.put(ClubAttributes.ATTACK,50);
		input.put(ClubAttributes.MIDFIELD,60);
		input.put(ClubAttributes.DEFENCE,70);
		HashMap.Entry<ClubAttributes, Integer> maxAttribute = CommonFunctions.maxClubAttribute(input);

		HashMap.Entry<ClubAttributes, Integer> result = new AbstractMap.SimpleEntry<ClubAttributes,Integer>(ClubAttributes.DEFENCE,70);
		assertEquals(result, maxAttribute);
	}

	@Test
	void mapClubAttributeToFormationTypeAttackTest() {
		FormationType result = CommonFunctions.mapClubAttributeToFormationType(ClubAttributes.ATTACK);
		assertEquals(result,FormationType.ATTACKING);
	}

	@Test
	void mapClubAttributeToFormationTypeMidfieldTest() {
		FormationType result = CommonFunctions.mapClubAttributeToFormationType(ClubAttributes.MIDFIELD);
		assertEquals(result,FormationType.NEUTRAL);
	}

	@Test
	void mapClubAttributeToFormationTypeDefenceTest() {
		FormationType result = CommonFunctions.mapClubAttributeToFormationType(ClubAttributes.DEFENCE);
		assertEquals(result,FormationType.DEFENSIVE);
	}
}