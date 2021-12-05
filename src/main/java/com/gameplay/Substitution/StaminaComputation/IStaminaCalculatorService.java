package com.gameplay.Substitution.StaminaComputation;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;

public interface IStaminaCalculatorService {
    public void computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven);
}
