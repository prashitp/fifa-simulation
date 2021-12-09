/**
 * @author Mayank Sareen
 */

package com.gameplay.Substitution.StaminaComputation;

import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.Substitution.StaminaFactors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaminaCalculatorController implements IStaminaCalculatorController {
    private static List<StaminaObserver> observers = new ArrayList<StaminaObserver>();

    @Override
    public void attach(StaminaObserver observer){
        observers.add(observer);
    }

    @Override
    public void computeStamina(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        for (PlayerModel player : playingEleven.keySet()) {
            HashMap<PlayerAttributes, Double> playerMap =
                    StaminaFactors.getMultiplicativeFactors().get(playingEleven.get(player));
            Double sum = 0.0;
            Integer staminaLoss = 0;
            Integer powerStamina = 0;
            for (PlayerAttributes playerAttributes : playerMap.keySet()) {
                staminaLoss = (int) (player.skills.get(playerAttributes) - playerMap.get(playerAttributes));
                player.skills.put(playerAttributes, staminaLoss);
                sum += staminaLoss;
            }
            powerStamina = (int)((sum / (playerMap.keySet().size())) *
                    StaminaFactors.getPercentageReducedByPosition().get(playingEleven.get(player)));
            player.skills.put(PlayerAttributes.POWER_STAMINA, powerStamina);
        }
        notifyAllObservers(playingEleven);
    }

    private void notifyAllObservers(HashMap<PlayerModel, PlayingPosition> playingEleven){
        for (StaminaObserver observer : observers) {
            observer.notifyUpdate(playingEleven);
        }
    }
}

