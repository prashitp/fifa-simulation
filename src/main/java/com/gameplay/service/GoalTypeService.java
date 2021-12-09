package com.gameplay.service;

import com.models.Criteria;
import com.models.GoalType;
import com.models.SetPieceType;

import java.util.*;
/**
 * @author prashitpatel
 */
public class GoalTypeService implements IGoalTypeService {
	public GoalType mapCriteriaToGoalType(Criteria criteria) {
		switch (criteria) {
			case PENALTY:
				return GoalType.PENALTY;
			case PLAYER_ATTRIBUTES:
				if(Math.random() > 0.9) {
					return  GoalType.OWN_GOAL;
				}
				return GoalType.OPEN_PLAY;
		}
		return GoalType.OPEN_PLAY;
	}

	public GoalType mapSetPieceToGoalType(SetPieceType setPieceType) {
		switch (setPieceType) {
			case FREE_KICK:
				return GoalType.FREE_KICK;
			case CORNER_KICK:
				return GoalType.CORNER;
		}
		return GoalType.CORNER;
	}

	public int generateWeightedIndex() {
		if(Math.random() > 0.4) {
			return 0;
		}
		return 1;
	}

	public List<Map.Entry<Criteria, Double>> sortCriteriaHashMap(HashMap<Criteria, Double> map) {
		List<Map.Entry<Criteria, Double>> mapList = new ArrayList<>(map.entrySet());
		mapList.sort(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.reverseOrder())));

		return mapList;
	}

	public List<Map.Entry<SetPieceType, Integer>> sortSetPieceHashMap(HashMap<SetPieceType, Integer> map) {
		List<Map.Entry<SetPieceType, Integer>> mapList = new ArrayList<>(map.entrySet());
		mapList.sort(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.reverseOrder())));

		return mapList;
	}
}

