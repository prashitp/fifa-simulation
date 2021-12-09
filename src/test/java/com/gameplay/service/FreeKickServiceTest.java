package com.gameplay.service;

import com.utils.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author vasugamdha
 */

public class FreeKickServiceTest {
    HashMap<PlayerModel, PlayingPosition> team1 = new HashMap<>();
    HashMap<PlayerModel,PlayingPosition> team2 = new HashMap<>();

    public FreeKickServiceTest(){
        team1.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
        team1.put(Constants.PLAYERS[12], PlayingPosition.MIDFIELDER);
        team1.put(Constants.PLAYERS[20], PlayingPosition.DEFENDER);
        team1.put(Constants.PLAYERS[39], PlayingPosition.GOALKEEPER);

        team2.put(Constants.PLAYERS[1], PlayingPosition.FORWARD);
        team2.put(Constants.PLAYERS[8], PlayingPosition.MIDFIELDER);
        team2.put(Constants.PLAYERS[13], PlayingPosition.DEFENDER);
        team2.put(Constants.PLAYERS[18], PlayingPosition.GOALKEEPER);
    }

    @Test
    public void getSetPieceTypeTest(){
        IKickService freeKick = new FreeKickService(team1,team2);
        assertEquals("List12", freeKick.getSetPiece().getClass().getSimpleName());
    }
}
