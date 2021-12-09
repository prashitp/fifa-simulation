package com.gameplay.service;

import com.models.PlayerModel;

import java.util.List;

/**
 * @author vasugamdha
 */

public interface ICardsService {
    void setup();

    List<PlayerModel> getPlayers();
}