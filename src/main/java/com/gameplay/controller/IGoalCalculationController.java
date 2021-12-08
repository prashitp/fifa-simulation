package com.gameplay.controller;

import com.models.ClubModel;
import java.util.HashMap;

/**
 * @author prashitpatel
 */
public interface IGoalCalculationController {
	HashMap<ClubModel,Integer> getScores();
}
