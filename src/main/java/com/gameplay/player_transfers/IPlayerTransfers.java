/**
 * @author Mayank Sareen
 */

package com.gameplay.player_transfers;

import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.List;

public interface IPlayerTransfers {
    public List<PlayerTransferWrapper> getPlayersForTransferPerTeam(List<PlayerModel> playersListForTransfer, List<PlayerAttributes> getPlayerRankingBySkills);
}
