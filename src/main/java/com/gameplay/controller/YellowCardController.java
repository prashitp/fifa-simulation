package com.gameplay.controller;

import com.gameplay.service.IYellowCardService;
import com.gameplay.service.YellowCardService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class YellowCardController implements IYellowCardController {
    IYellowCardService yellowCardService;

    public YellowCardController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        yellowCardService = new YellowCardService(team1, team2);
    }

    public List<PlayerModel> getYellowCardPlayers(){
        return yellowCardService.getPlayers();
    }
}
