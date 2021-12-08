package com.gameplay.controller;

import com.models.ClubModel;
import com.models.Goal;

import java.util.HashMap;
import java.util.List;

/**
 * @author prashitpatel
 */
public interface IScoreLineController {
	HashMap<ClubModel, List<Goal>> getScoreLine();
}
