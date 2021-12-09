package com.gameplay.PlayerTransfers;

/**
 * @author: mayanksareen
 */
import com.gameplay.PlayerTransfers.controller.ForwardPlayerTransfers;
import com.gameplay.PlayerTransfers.controller.IForwardPlayerTransfers;
import com.models.PlayerModel;
import com.utils.Constants;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForwardPlayerTransfersTest {
    List<PlayerModel> team = new ArrayList<>();
    public ForwardPlayerTransfersTest(){
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
        IForwardPlayerTransfers iForwardPlayerTransfers = new ForwardPlayerTransfers();
        assertEquals("ArrayList", iForwardPlayerTransfers.getForwardsForTransferPerTeam(team).getClass().getSimpleName());
    }
}