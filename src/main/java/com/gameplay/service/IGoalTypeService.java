package com.gameplay.service;

import com.models.Criteria;
import com.models.GoalType;
import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author prashitpatel
 */
public interface IGoalTypeService {
	GoalType mapCriteriaToGoalType(Criteria criteria);

	GoalType mapSetPieceToGoalType(SetPieceType setPieceType);

	int generateWeightedIndex();

	List<Map.Entry<Criteria, Double>> sortCriteriaHashMap(HashMap<Criteria, Double> map);

	List<Map.Entry<SetPieceType, Integer>> sortSetPieceHashMap(HashMap<SetPieceType, Integer> map);
}
