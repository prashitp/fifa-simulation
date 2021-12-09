package com.gameplay.service;

import com.models.Criteria;
import com.models.GoalType;
import com.models.SetPieceType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author prashitpatel
 */
class GoalTypeServiceTest {
	IGoalTypeService goalTypeService = new GoalTypeService();

	@Test
	void mapCriteriaToGoalTypeTest() {
		GoalType goalType = goalTypeService.mapCriteriaToGoalType(Criteria.PENALTY);
		assertEquals(goalType, GoalType.PENALTY);
	}

	@Test
	void mapCriteriaToGoalTypeNoMatchTest() {
		GoalType goalType = goalTypeService.mapCriteriaToGoalType(Criteria.SET_PIECES);
		assertEquals(goalType, GoalType.OPEN_PLAY);
	}


	@Test
	void mapSetPieceToGoalTypeTest() {
		GoalType goalType = goalTypeService.mapSetPieceToGoalType(SetPieceType.FREE_KICK);
		assertEquals(goalType, GoalType.FREE_KICK);
	}

	@Test
	void sortCriteriaHashMapTest() {
		HashMap<Criteria, Double> map = new HashMap<>();
		map.put(Criteria.FORMATION,0.5);
		map.put(Criteria.SET_PIECES,0.6);
		List<Map.Entry<Criteria, Double>> mapList = goalTypeService.sortCriteriaHashMap(map);
		assertEquals(2,mapList.size());
	}

	@Test
	void sortSetPieceHashMapTest() {
		HashMap<SetPieceType, Integer> map = new HashMap<>();
		map.put(SetPieceType.FREE_KICK,5);
		map.put(SetPieceType.CORNER_KICK,6);
		List<Map.Entry<SetPieceType, Integer>> mapList = goalTypeService.sortSetPieceHashMap(map);
		assertEquals(2,mapList.size());
	}
}