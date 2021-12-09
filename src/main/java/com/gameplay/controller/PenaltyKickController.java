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

public class PenaltyKickController implements IPenaltyKickController {
    IKickService penaltyKickService;

    public PenaltyKickController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.penaltyKickService = new CornerKickService(team1,team2);
    }

    @Override
    public List<Integer> getPenaltyKicks() {
        return penaltyKickService.getSetPiece();
    }

}
