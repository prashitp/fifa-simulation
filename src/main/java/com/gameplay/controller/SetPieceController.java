package com.gameplay.controller;

import com.gameplay.service.ISetPieceService;
import com.gameplay.service.SetPieceService;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class SetPieceController implements ISetPieceController {

    ISetPieceService setPieceService;

    public SetPieceController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        setPieceService = new SetPieceService(team1,team2);
    }

    @Override
    public HashMap<SetPieceType, List<Integer>> getSetPieces() {
        return setPieceService.getSetPieces();
    }

}
