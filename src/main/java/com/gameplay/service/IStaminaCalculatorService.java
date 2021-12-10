package com.gameplay.service;
/**
 * @author mayanksareen
 */
import com.gameplay.controller.StaminaObserver;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import java.util.HashMap;
import java.util.Set;

public interface IStaminaCalculatorService {
    public void attach(StaminaObserver observer);
    public Set<String> computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
