/**
 * @author Mayank Sareen
 */
package com.gameplay.player_transfers.goalkeeper;

import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.HashMap;
import java.util.List;

public interface IGoalKeeperPlayerTransfers {
    public List<PlayerTransferWrapper> getGoalKeeperForTransferPerTeam(List<PlayerModel> goalKeeperListForTransfers);
    public List<PlayerModel> computeBestSellingGoalies(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
    }
