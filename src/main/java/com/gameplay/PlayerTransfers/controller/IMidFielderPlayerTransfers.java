package com.gameplay.PlayerTransfers.controller;
/**
 * @author: mayanksareen
 */
import com.models.PlayerModel;
import com.models.PlayerTransferWrapper;
import java.util.HashMap;
import java.util.List;

public interface IMidFielderPlayerTransfers {
    public List<PlayerTransferWrapper> getMidFieldersForTransferPerTeam(List<PlayerModel> midfielderListForTransfers);
    public List<PlayerModel> computeBestSellingMidFields(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
}
