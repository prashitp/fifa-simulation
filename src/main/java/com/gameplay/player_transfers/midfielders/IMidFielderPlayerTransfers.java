/**
 * @author Mayank Sareen
 */
package com.gameplay.player_transfers.midfielders;

import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.HashMap;
import java.util.List;

public interface IMidFielderPlayerTransfers {
    public List<PlayerTransferWrapper> getMidFieldersForTransferPerTeam(List<PlayerModel> midfielderListForTransfers);
    public List<PlayerModel> computeBestSellingMidFields(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
}
