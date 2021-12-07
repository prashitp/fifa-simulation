package com.gameplay.Fouls;

import com.models.PlayerModel;
import com.models.gameplay.CardType;

import java.util.HashMap;
import java.util.List;

public interface ICardsController {

    HashMap<CardType, List<PlayerModel>> getFouls();
}
