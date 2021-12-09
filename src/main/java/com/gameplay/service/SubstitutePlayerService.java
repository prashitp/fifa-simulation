package com.gameplay.service;
/**
 * @author mayanksareen
 */
import com.gameplay.controller.StaminaObserver;
import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubstitutePlayerService extends StaminaObserver {
    private static final Integer MINIMUM_STAMINA_THRESHOLD = 30;
    private static final Integer MAXIMUM_ALLOWED_SUBSTITUTIONS = 5;
    private static final Integer PLAYER_MINIMUM_STAMINA_COUNTER = 40;

    protected List<PlayerModel> candidateForSubstitution = new ArrayList<>();
    protected HashMap<PlayingPosition, List<PlayerModel>> availablePlayers = new HashMap<>();
    public SubstitutePlayerService(IStaminaCalculatorService staminaCalculator){
        this.staminaCalculator = staminaCalculator;
        this.staminaCalculator.attach(this);
    }

    @Override
    public List<String> notifyUpdate(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        return performPlayerSubstitution(playingEleven);
    }

    private List<String> performPlayerSubstitution(HashMap<PlayerModel, PlayingPosition> playingEleven) {
        List<String> output = new ArrayList<>();
        String clubName="";
        for (PlayerModel player : playingEleven.keySet()) {
            if (player.skills.get(PlayerAttributes.POWER_STAMINA) < MINIMUM_STAMINA_THRESHOLD) {
                setAvailablePlayersForSubstitution(player.club, playingEleven.get(player));
                clubName = player.club;
            }
        }
        output.add("***** Substitutions - " + clubName + "*****");
        for (PlayingPosition position: availablePlayers.keySet()) {
            availablePlayers.get(position).sort((p1, p2) -> (p2.overall - p1.overall));
            for (Integer i = 0; i < candidateForSubstitution.size() && i < MAXIMUM_ALLOWED_SUBSTITUTIONS; i++) {
               output.add("Player " + candidateForSubstitution.get(i).getPlayerName()
                        + " has been substituted with " + availablePlayers.get(position).get(i).getPlayerName());
            }
        }
        return output;
    }

    private void setAvailablePlayersForSubstitution(String clubName, PlayingPosition position) {
        List<PlayerModel> allPlayers = Arrays.asList(Constants.PLAYERS);
        for (PlayerModel player: allPlayers) {
            if (player.club.equals(clubName) && player.skills.get(PlayerAttributes.POWER_STAMINA) >=
                    PLAYER_MINIMUM_STAMINA_COUNTER + MINIMUM_STAMINA_THRESHOLD) {
                candidateForSubstitution.add(player);
            }
        }
        availablePlayers.put(position, candidateForSubstitution);
    }
}

