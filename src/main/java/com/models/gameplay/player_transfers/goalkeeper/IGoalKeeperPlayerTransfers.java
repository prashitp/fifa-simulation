package com.models.gameplay.player_transfers.goalkeeper;

import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.List;

public interface IGoalKeeperPlayerTransfers {
    public List<PlayerTransferWrapper> getGoalKeeperForTransferPerTeam(List<PlayerModel> goalKeeperListForTransfers);
}
