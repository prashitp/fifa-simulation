package com.gameplay.player_transfers.controller.goalkeeper;
/**
 * @author: mayanksareen
 */
import com.models.PlayerModel;
import com.models.PlayerTransferWrapper;
import java.util.HashMap;
import java.util.List;

public interface IGoalKeeperPlayerTransfers {
    public List<PlayerTransferWrapper> getGoalKeeperForTransferPerTeam(List<PlayerModel> goalKeeperListForTransfers);
    public List<PlayerModel> computeBestSellingGoalies(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
    }
