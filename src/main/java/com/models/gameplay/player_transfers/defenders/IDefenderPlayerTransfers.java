package com.models.gameplay.player_transfers.defenders;

import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.List;

public interface IDefenderPlayerTransfers {
    public List<PlayerTransferWrapper> getDefendersForTransferPerTeam(List<PlayerModel> defendersListForTransfer);
}
