package com.gameplay.service;

import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;

public class SetPieceService implements ISetPieceService{

    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;

    public SetPieceService(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    @Override
    public HashMap<SetPieceType, List<Integer>> getSetPieces() {
        HashMap<SetPieceType, List<Integer>> setPieces = new HashMap<>();

        KickService cornerKicks = new CornerKickService(team1, team2);
        KickService freeKicks = new FreeKickService(team1, team2);
        KickService penaltyKicks = new PenaltyKickService(team1, team2);

        setPieces.put(SetPieceType.CORNER_KICK, cornerKicks.getSetPiece());
        setPieces.put(SetPieceType.FREE_KICK, freeKicks.getSetPiece());
        setPieces.put(SetPieceType.PENALTY_KICK, penaltyKicks.getSetPiece());

        return setPieces;
    }
}
