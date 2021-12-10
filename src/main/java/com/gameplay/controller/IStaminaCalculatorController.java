package com.gameplay.controller;
/**
 * @author mayanksareen
 */
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;
import java.util.Set;

public interface IStaminaCalculatorController {
    public Set<String> computeStamina(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2);
}
