package com.gameplay.controller;
/**
 * @author Mayank Sareen
 */
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;

public interface IStaminaCalculatorController {
    public Boolean computeStamina(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2);
}
