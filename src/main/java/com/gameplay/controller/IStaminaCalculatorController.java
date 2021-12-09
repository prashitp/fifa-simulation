package com.gameplay.controller;
/**
 * @author Mayank Sareen
 */
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

public interface IStaminaCalculatorController {
    public List<String> computeStamina(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2);
}
