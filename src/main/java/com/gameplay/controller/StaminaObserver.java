package com.gameplay.controller;
/**
 * @author mayanksareen
 */
import com.gameplay.service.IStaminaCalculatorService;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;
import java.util.List;

public abstract class StaminaObserver {
    protected IStaminaCalculatorService staminaCalculator;
    public abstract List<String> notifyUpdate(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
