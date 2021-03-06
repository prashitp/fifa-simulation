package com.gameplay.PlayerTransfers.PlayerRearrangement.service;
/**
 * @author: mayanksareen
 */
import com.models.ClubModel;
import com.models.PlayerTransferWrapper;
import java.util.HashMap;
import java.util.List;

public interface IReArrangePlayersByClubRanks {
    public HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> reArrangeClubToPlayerMappings(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
    public List<ClubModel> getHighestPayingClub();
}
