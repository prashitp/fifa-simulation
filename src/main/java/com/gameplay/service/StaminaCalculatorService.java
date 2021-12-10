package com.gameplay.service;
/**
 * @author mayanksareen
 */
import com.gameplay.controller.StaminaObserver;
import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.StaminaFactors;

import java.util.*;

public class StaminaCalculatorService implements IStaminaCalculatorService {
    private static List<StaminaObserver> observers = new ArrayList<StaminaObserver>();

    @Override
    public void attach(StaminaObserver observer){
        observers.add(observer);
    }

    @Override
    public Set<String> computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        for (PlayerModel player : playingEleven.keySet()) {
            HashMap<PlayerAttributes, Double> playerMap =
                    StaminaFactors.getMultiplicativeFactors().get(playingEleven.get(player));
            Double sum = 0.0;
            Integer staminaLoss = 0;
            Integer powerStamina = 0;
            for (PlayerAttributes playerAttributes : playerMap.keySet()) {
                staminaLoss = (int) (player.skills.get(playerAttributes) - playerMap.get(playerAttributes));
                sum += staminaLoss;
            }
            powerStamina = (int)((sum / (playerMap.keySet().size())) *
                    StaminaFactors.getPercentageReducedByPosition().get(playingEleven.get(player)));
            player.skills.put(PlayerAttributes.POWER_STAMINA, powerStamina);
        }
        return notifyAllObservers(playingEleven);
    }

    private Set<String> notifyAllObservers(HashMap<PlayerModel, PlayingPosition> playingEleven){
        Set<String> outputOfAllObservers = new HashSet<>();
        for (StaminaObserver observer : observers) {
            outputOfAllObservers.addAll(observer.notifyUpdate(playingEleven));
        }
        return outputOfAllObservers;
    }
}

