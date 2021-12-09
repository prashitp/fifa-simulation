package com.gameplay.PlayerTransfers.controller;
/**
 * @author: mayanksareen
 */
import com.models.PlayerModel;
import com.models.PlayerTransferWrapper;
import java.util.HashMap;
import java.util.List;

public interface IDefenderPlayerTransfers {
    public List<PlayerTransferWrapper> getDefendersForTransferPerTeam(List<PlayerModel> defendersListForTransfer);
    public List<PlayerModel> computeBestSellingDefenders(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
    }
