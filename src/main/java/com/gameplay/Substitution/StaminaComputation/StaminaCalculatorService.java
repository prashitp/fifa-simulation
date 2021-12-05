package com.gameplay.Substitution.StaminaComputation;

import com.gameplay.Substitution.SubstitutePlayers;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;

/**
 * @author Mayank Sareen
 */
public class StaminaCalculatorService implements IStaminaCalculatorService {
    @Override
    public void computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        IStaminaCalculatorController staminaCalculatorController = new StaminaCalculatorController();
        new SubstitutePlayers(staminaCalculatorController);
        staminaCalculatorController.computeStamina(playingEleven);
    }
}
