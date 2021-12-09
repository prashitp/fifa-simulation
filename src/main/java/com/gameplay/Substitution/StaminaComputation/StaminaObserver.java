/**
 * @author Mayank Sareen
 */
package com.gameplay.Substitution.StaminaComputation;

import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;

public abstract class StaminaObserver {
    protected IStaminaCalculatorController staminaCalculator;
    public abstract void notifyUpdate(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
