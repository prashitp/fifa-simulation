/**
 * @author Mayank Sareen
 */
package com.gameplay.controller;

import com.gameplay.service.SubstitutePlayerService;
import com.gameplay.service.IStaminaCalculatorService;
import com.gameplay.service.StaminaCalculatorService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;

public class StaminaCalculatorController implements IStaminaCalculatorController {
    @Override
    public Boolean computeStamina(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        IStaminaCalculatorService staminaCalculatorService = new StaminaCalculatorService();
        new SubstitutePlayerService(staminaCalculatorService);
        staminaCalculatorService.computeStamina(team1);
        staminaCalculatorService.computeStamina(team2);
        return true;
    }

}
