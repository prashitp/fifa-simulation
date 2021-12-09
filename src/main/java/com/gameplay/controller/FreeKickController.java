package com.gameplay.controller;

import com.gameplay.service.CornerKickService;
import com.gameplay.service.IKickService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class FreeKickController implements IFreeKickController{
    IKickService freeKickService;

    public FreeKickController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.freeKickService = new CornerKickService(team1,team2);
    }

    @Override
    public List<Integer> getFreeKicks() {
        return freeKickService.getSetPiece();
    }
}
