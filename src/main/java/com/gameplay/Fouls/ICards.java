package com.gameplay.Fouls;

import com.models.PlayerModel;

import java.util.List;

/**
 * @author vasugamdha
 */

public interface ICards {
    void setup();

    List<PlayerModel> getPlayers();
}