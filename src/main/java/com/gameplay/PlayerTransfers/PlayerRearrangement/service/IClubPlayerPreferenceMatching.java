package com.gameplay.PlayerTransfers.PlayerRearrangement.service;
/**
 * @author: mayanksareen
 */
import java.util.ArrayList;

public interface IClubPlayerPreferenceMatching {
    public ArrayList<Integer> matchClubsAndPlayersBasedOnTheirPreferences(ArrayList<ArrayList<Integer>> preferenceIndices);
}
