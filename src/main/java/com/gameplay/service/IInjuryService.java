package com.gameplay.service;

import com.models.PlayerModel;

import java.util.HashMap;

/**
 * @author vasugamdha
 */

public interface IInjuryService {
    void setup();
    HashMap<PlayerModel, Integer> getInjuredPlayers();
}
