package com.gameplay.controller;

import com.gameplay.service.ICardsService;
import com.gameplay.service.RedCardService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class RedCardController implements IRedCardController {
    ICardsService redCardService;

    public RedCardController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        redCardService = new RedCardService(team1, team2);
    }

    public List<PlayerModel> getRedCardPlayers(){
        return redCardService.getPlayers();
    }
}
