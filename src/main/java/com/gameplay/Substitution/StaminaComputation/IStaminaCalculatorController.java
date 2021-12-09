/**
 * @author Mayank Sareen
 */
package com.gameplay.Substitution.StaminaComputation;

import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;

public interface IStaminaCalculatorController {
    public void attach(StaminaObserver observer);
    public void computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
