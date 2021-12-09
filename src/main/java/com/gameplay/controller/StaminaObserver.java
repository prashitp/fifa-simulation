/**
 * @author Mayank Sareen
 */
package com.gameplay.controller;

import com.gameplay.service.IStaminaCalculatorService;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;

public abstract class StaminaObserver {
    protected IStaminaCalculatorService staminaCalculator;
    public abstract void notifyUpdate(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
