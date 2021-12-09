package com.models.gameplay.player_transfers.forwards;

import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.HashMap;
import java.util.List;
/**
 * @author Mayank Sareen
 */
public interface IForwardPlayerTransfers {
    public List<PlayerTransferWrapper> getForwardsForTransferPerTeam(List<PlayerModel> forwardsListForTransfer);
    public  List<PlayerModel> computeBestSellingForwards(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
}
