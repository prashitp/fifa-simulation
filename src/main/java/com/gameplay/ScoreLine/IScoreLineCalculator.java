package com.gameplay.ScoreLine;

import com.models.ClubModel;

import java.util.HashMap;

/**
 * @author prashitpatel
 */
public interface IScoreLineCalculator {
	HashMap<ClubModel,Integer> getScoreLine();
}
