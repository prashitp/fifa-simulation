package com.gameplay.player_transfers;
/**
 * @author: mayanksareen
 */

import com.gameplay.PlayerTransfers.controller.DefenderPlayerTransfers;
import com.gameplay.PlayerTransfers.controller.IDefenderPlayerTransfers;
import com.models.PlayerModel;
import com.utils.Constants;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefenderPlayerTransfersTest {
    List<PlayerModel> team = new ArrayList<>();
    public DefenderPlayerTransfersTest(){
        team.add(Constants.PLAYERS[0]);
        team.add(Constants.PLAYERS[12]);
        team.add(Constants.PLAYERS[20]);
        team.add(Constants.PLAYERS[39]);
        team.add(Constants.PLAYERS[1]);
        team.add(Constants.PLAYERS[8]);
        team.add(Constants.PLAYERS[13]);
        team.add(Constants.PLAYERS[18]);
    }

    @Test
    void getDefendersForTransferPerTeamTest() {
        IDefenderPlayerTransfers iDefenderPlayerTransfers = new DefenderPlayerTransfers();
        iDefenderPlayerTransfers.getDefendersForTransferPerTeam(team);
        assertEquals("ArrayList", iDefenderPlayerTransfers.getDefendersForTransferPerTeam(team).getClass().getSimpleName());
    }
}