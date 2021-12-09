package com.gameplay.controller;

import com.models.PlayerModel;

import java.util.HashMap;

/**
 * @author vasugamdha
 */

public interface IInjuryController {
    HashMap<PlayerModel, Integer> getInjuredPlayers();
}
