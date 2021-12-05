package com.gameplay.SetPiece;

import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.SetPieceType;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class SetPieceController implements ISetPieceController {

    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;

    public SetPieceController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    @Override
    public HashMap<SetPieceType, List<Integer>> getSetPieces() {
        HashMap<SetPieceType, List<Integer>> setPieces = new HashMap<>();

        SetPiece cornerKicks = new CornerKick(team1, team2);
        SetPiece freeKicks = new FreeKick(team1, team2);
        SetPiece penaltyKicks = new PenaltyKick(team1, team2);

        setPieces.put(SetPieceType.CORNER_KICK, cornerKicks.getSetPiece());
        setPieces.put(SetPieceType.FREE_KICK, freeKicks.getSetPiece());
        setPieces.put(SetPieceType.PENALTY_KICK, penaltyKicks.getSetPiece());

        return setPieces;
    }

}
