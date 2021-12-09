package com.gameplay.player_transfers;
/**
 * @author: mayanksareen
 */
import com.gameplay.PlayerTransfers.controller.IMidFielderPlayerTransfers;
import com.gameplay.PlayerTransfers.controller.MidFielderPlayerTransfers;
import com.models.PlayerModel;
import com.utils.Constants;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MidFielderPlayerTransfersTest {
    List<PlayerModel> team = new ArrayList<>();
    public MidFielderPlayerTransfersTest(){
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
    void getMidFieldersForTransferPerTeamTest() {
        IMidFielderPlayerTransfers iMidFielderPlayerTransfers = new MidFielderPlayerTransfers();
        assertEquals("ArrayList", iMidFielderPlayerTransfers.getMidFieldersForTransferPerTeam(team).getClass().getSimpleName());
    }
}