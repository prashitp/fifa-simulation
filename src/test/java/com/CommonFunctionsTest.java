package com;

import com.models.ClubAttributes;
import com.models.gameplay.TeamSelection.FormationType;
import org.junit.jupiter.api.Test;
import java.util.AbstractMap;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author prashitpatel
 */
class CommonFunctionsTest {
	@Test
	void maxClubAttributeTest() {
		HashMap<ClubAttributes,Integer> input = new HashMap<>();
		input.put(ClubAttributes.Attack,50);
		input.put(ClubAttributes.Midfield,60);
		input.put(ClubAttributes.Defence,70);
		HashMap.Entry<ClubAttributes, Integer> maxAttribute = CommonFunctions.maxClubAttribute(input);

		HashMap.Entry<ClubAttributes, Integer> result = new AbstractMap.SimpleEntry<ClubAttributes,Integer>(ClubAttributes.Defence,70);
		assertEquals(result, maxAttribute);
	}

	@Test
	void mapClubAttributeToFormationTypeAttackTest() {
		FormationType result = CommonFunctions.mapClubAttributeToFormationType(ClubAttributes.Attack);
		assertEquals(result,FormationType.Attacking);
	}

	@Test
	void mapClubAttributeToFormationTypeMidfieldTest() {
		FormationType result = CommonFunctions.mapClubAttributeToFormationType(ClubAttributes.Midfield);
		assertEquals(result,FormationType.Neutral);
	}

	@Test
	void mapClubAttributeToFormationTypeDefenceTest() {
		FormationType result = CommonFunctions.mapClubAttributeToFormationType(ClubAttributes.Defence);
		assertEquals(result,FormationType.Defensive);
	}
}