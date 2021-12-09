package com.models.gameplay.player_transfers.player_rearrangement;

import java.util.ArrayList;

public interface IClubPlayerPreferenceMatching {
    public ArrayList<Integer> matchClubsAndPlayersBasedOnTheirPreferences(ArrayList<ArrayList<Integer>> preferenceIndices);
}
