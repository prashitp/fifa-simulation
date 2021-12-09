package com.models.gameplay.player_transfers.midfielders;

import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.List;

public interface IMidFielderPlayerTransfers {
    public List<PlayerTransferWrapper> getMidFieldersForTransferPerTeam(List<PlayerModel> midfielderListForTransfers);
}
