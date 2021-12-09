package com.models.gameplay.player_transfers.player_rearrangement;
/**
 * @author: Mayank Sareen
 */
import java.util.ArrayList;

public class ClubPlayerPreferenceMatching implements IClubPlayerPreferenceMatching {

    private boolean isClubPrefersCurrentOverPrevious(ArrayList<ArrayList<Integer>> preferenceIndices, int club,
                                   int player, int currentPlayer) {
        for (int i = 0; i < 4; i++) {
            if (preferenceIndices.get(club).get(i) == currentPlayer) {
                return true;
            }
            if (preferenceIndices.get(club).get(i) == player) {
                return false;
            }
        }
        return false;
    }

    @Override
    public  ArrayList<Integer> matchClubsAndPlayersBasedOnTheirPreferences( ArrayList<ArrayList<Integer>> preferenceIndices) {
        ArrayList<Integer> matchedPreferences = new ArrayList<>(){
            {   add(-1);
                add(-1);
                add(-1);
                add(-1);
            }
        };
        ArrayList<Boolean> freePlayers = new ArrayList<>(){
            {   add(false);
                add(false);
                add(false);
                add(false);
            }
        };
        int freePairCounter = 4;
        while (freePairCounter > 0) {
            int player;
            for (player = 0; player < 4; player++) {
                if (!freePlayers.get(player)) {
                    break;
                }
            }
            for (int i = 0; i < 4 && !freePlayers.get(player); i++) {
                int club = preferenceIndices.get(player).get(i);
                if (matchedPreferences.get(club - 4) == -1) {
                    matchedPreferences.set((club - 4), player);
                    freePlayers.set(player, true);
                    freePairCounter--;
                } else {
                    int currentPlayer = matchedPreferences.get(club - 4);
                    if (!isClubPrefersCurrentOverPrevious(preferenceIndices, club, player, currentPlayer)) {
                        matchedPreferences.set(club - 4, player);
                        freePlayers.set(player, true);
                        freePlayers.set(currentPlayer,false);
                    }
                }
            }
        }
        return matchedPreferences;
    }
}
