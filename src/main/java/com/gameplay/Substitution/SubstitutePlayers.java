package com.gameplay.Substitution;

import com.Constants;
import com.gameplay.Substitution.StaminaComputation.IStaminaCalculatorController;
import com.gameplay.Substitution.StaminaComputation.StaminaObserver;
import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mayank Sareen
 */

public class SubstitutePlayers extends StaminaObserver {
    private static final Integer MINIMUM_STAMINA_THRESHOLD = 30;
    protected List<PlayerModel> candidateForSubstitution = new ArrayList<>();
    protected HashMap<PlayingPosition, List<PlayerModel>> availablePlayers = new HashMap<>();
    public SubstitutePlayers(IStaminaCalculatorController staminaCalculator){
        this.staminaCalculator = staminaCalculator;
        this.staminaCalculator.attach(this);
    }

    @Override
    public void notifyUpdate(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        performPlayerSubstitution(playingEleven);
    }

    private void performPlayerSubstitution(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        for (PlayerModel player : playingEleven.keySet()) {
            if (player.skills.get(PlayerAttributes.POWER_STAMINA) < MINIMUM_STAMINA_THRESHOLD) {
                setAvailablePlayersForSubstitution(player.club, playingEleven.get(player));
            }
        }
        for (PlayingPosition position: availablePlayers.keySet()) {
            System.out.println("Position" + position + "players::" + availablePlayers.get(position));
        }
    }

    private void setAvailablePlayersForSubstitution(String clubName, PlayingPosition position) {
        List<PlayerModel> allPlayers = Arrays.asList(Constants.PLAYERS);
        for (PlayerModel player: allPlayers) {
            if (player.club.equals(clubName) && player.skills.get(PlayerAttributes.POWER_STAMINA) >= 40 + MINIMUM_STAMINA_THRESHOLD) {
                candidateForSubstitution.add(player);
            }
        }
        availablePlayers.put(position, candidateForSubstitution);
    }
}

