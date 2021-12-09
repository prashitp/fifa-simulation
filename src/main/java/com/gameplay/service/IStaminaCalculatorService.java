/**
 * @author Mayank Sareen
 */
package com.gameplay.service;

import com.gameplay.controller.StaminaObserver;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;

public interface IStaminaCalculatorService {
    public void attach(StaminaObserver observer);
    public Boolean computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
