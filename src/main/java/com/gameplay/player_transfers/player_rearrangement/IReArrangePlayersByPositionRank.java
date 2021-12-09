/**
 * @author: Mayank Sareen
 */
package com.gameplay.player_transfers.player_rearrangement;

import com.models.gameplay.player_transfers.PlayerTransferWrapper;
import java.util.List;

public interface IReArrangePlayersByPositionRank {
    public List<PlayerTransferWrapper> movePlayersByComputedRank(List<PlayerTransferWrapper> players);
}
