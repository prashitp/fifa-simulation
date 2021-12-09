package com.gameplay.player_transfers.controller.player_rearrangement.service;
/**
 * @author: mayanksareen
 */
import java.util.ArrayList;

public interface IClubPlayerPreferenceMatching {
    public ArrayList<Integer> matchClubsAndPlayersBasedOnTheirPreferences(ArrayList<ArrayList<Integer>> preferenceIndices);
}
