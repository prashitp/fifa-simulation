package com.gameplay.controller;
/**
 * @author mayankSareen
 */
import com.gameplay.service.SubstitutePlayerService;
import com.gameplay.service.IStaminaCalculatorService;
import com.gameplay.service.StaminaCalculatorService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StaminaCalculatorController implements IStaminaCalculatorController {
    Set<String> output;
    @Override
    public Set<String> computeStamina(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        output = new HashSet<>();
        IStaminaCalculatorService staminaCalculatorService = new StaminaCalculatorService();
        new SubstitutePlayerService(staminaCalculatorService);
        output.addAll(staminaCalculatorService.computeStamina(team1));
        output.addAll(staminaCalculatorService.computeStamina(team2));
        return output;
    }
}
