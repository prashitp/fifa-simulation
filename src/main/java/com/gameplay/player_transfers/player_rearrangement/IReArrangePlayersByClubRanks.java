/**
 * @author: Mayank Sareen
 */
package com.gameplay.player_transfers.player_rearrangement;

import com.models.ClubModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;
import java.util.HashMap;
import java.util.List;

public interface IReArrangePlayersByClubRanks {
    public HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> reArrangeClubToPlayerMappings(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap);
    public List<ClubModel> getHighestPayingClub();
}
