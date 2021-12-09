/**
 * @author Mayank Sareen
 */
package com.gameplay.Substitution.StaminaComputation;

import com.gameplay.Substitution.SubstitutePlayers;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.TeamSelection.Lineup;

import java.util.HashMap;

public class StaminaCalculatorService implements IStaminaCalculatorService {
    @Override
    public void computeStamina(Lineup lineup) {
        HashMap<PlayerModel, PlayingPosition> playingEleven = lineup.getplaying11();
        IStaminaCalculatorController staminaCalculatorController = new StaminaCalculatorController();
        new SubstitutePlayers(staminaCalculatorController);
        staminaCalculatorController.computeStamina(playingEleven);
    }
}
