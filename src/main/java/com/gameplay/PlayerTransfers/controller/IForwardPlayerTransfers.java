package com.gameplay.PlayerTransfers.controller;
/**
 * @author: mayanksareen
 */
import com.models.PlayerModel;
import com.models.PlayerTransferWrapper;
import java.util.HashMap;
import java.util.List;

public interface IForwardPlayerTransfers {
    public List<PlayerTransferWrapper> getForwardsForTransferPerTeam(List<PlayerModel> forwardsListForTransfer);
    public  List<PlayerModel> computeBestSellingForwards(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
}
