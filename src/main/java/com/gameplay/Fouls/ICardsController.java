package com.gameplay.Fouls;

import com.models.PlayerModel;
import com.models.gameplay.CardType;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public interface ICardsController {

    HashMap<CardType, List<PlayerModel>> fetchFouls();
}
