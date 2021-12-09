/**
 * @author Mayank Sareen
 */
package com.gameplay.player_transfers;

import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.gameplay.player_transfers.player_rearrangement.IReArrangePlayersByPositionRank;
import com.gameplay.player_transfers.player_rearrangement.ReArrangePlayersByRank;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerTransfers implements IPlayerTransfers {
    IReArrangePlayersByPositionRank shiftPlayers = new ReArrangePlayersByRank();
    @Override
    public List<PlayerTransferWrapper> getPlayersForTransferPerTeam(List<PlayerModel> playersListForTransfer, List<PlayerAttributes> getPlayerRankingBySkills) {
        List<PlayerTransferWrapper> rankedPlayersForTransfer = getTopPlayersForTransfer(playersListForTransfer, getPlayerRankingBySkills);
        shiftPlayers.movePlayersByComputedRank(rankedPlayersForTransfer);
        return rankedPlayersForTransfer;
    }

    private List<PlayerTransferWrapper> getTopPlayersForTransfer(List<PlayerModel>playersWithExpiringContract, List<PlayerAttributes> getPlayerRankingBySkills) {
        List<PlayerTransferWrapper> topPlayers = new ArrayList<>();
        int ranking = 0;
        Boolean isNearRetirementAge = false;
        for (PlayerModel player : playersWithExpiringContract) {
            ranking = 0;
            ranking += Math.floor(computeRankBasedOnPlayerSkills(player.skills, getPlayerRankingBySkills ) * 0.5);
            ranking += Math.floor((player.overall * 0.8) + (player.potential * 1.2));
            isNearRetirementAge = checkIfRetirementAge(player);
            topPlayers.add(new PlayerTransferWrapper(ranking, player, isNearRetirementAge));
        }
        return topPlayers;
    }

    private static int computeRankBasedOnPlayerSkills(HashMap<PlayerAttributes, Integer> playerAttributesMap, List<PlayerAttributes> playerAttributes) {
        int ranking = 0;
        for (PlayerAttributes attributes: playerAttributesMap.keySet()) {
            if (playerAttributes.contains(attributes)) {
                ranking += playerAttributesMap.get(attributes);
            }
        }
        return ranking;
    }

    private static Boolean checkIfRetirementAge(PlayerModel player) {
        return player.age >= 35;
    }

}
