package com.gameplay.controller;

import com.gameplay.service.IInjuryService;
import com.gameplay.service.InjuryService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;

/**
 * @author vasugamdha
 */

public class InjuryController implements IInjuryController{
    IInjuryService injuryService;

    public InjuryController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2){
        injuryService = new InjuryService(team1,team2);
    }

    @Override
    public HashMap<PlayerModel, Integer> getInjuredPlayers(){
        return injuryService.getInjuredPlayers();
    }
}
