package com.models.gameplay.player_transfers.defenders;

import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;
import com.models.gameplay.player_transfers.player_rearrangement.IReArrangePlayersByPositionRank;
import com.models.gameplay.player_transfers.player_rearrangement.ReArrangePlayersByRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mayank Sareen
 */

public class DefenderPlayerTransfers implements IDefenderPlayerTransfers {
    private static DefenderPlayerTransfers defenderPlayerTransfers;
    IReArrangePlayersByPositionRank shiftPlayers = new ReArrangePlayersByRank();
    public static DefenderPlayerTransfers getInstance() {
        if (defenderPlayerTransfers == null) {
            defenderPlayerTransfers = new DefenderPlayerTransfers();
        }
        return defenderPlayerTransfers;
    }

    @Override
    public List<PlayerTransferWrapper> getDefendersForTransferPerTeam(List<PlayerModel> defendersListForTransfer) {
        List<PlayerTransferWrapper> rankedDefenderForTransfer = getTopDefendersForTransfer(defendersListForTransfer);
        shiftPlayers.movePlayersByComputedRank(rankedDefenderForTransfer);
        return rankedDefenderForTransfer;
    }

    private List<PlayerTransferWrapper> getTopDefendersForTransfer(List<PlayerModel>playersWithExpiringContract) {
        List<PlayerTransferWrapper> topDefenders = computeRanking(playersWithExpiringContract);
        return topDefenders;
    }

    private List<PlayerTransferWrapper> computeRanking(List<PlayerModel> playersToBeTransferred) {
        List<PlayerTransferWrapper> playerTransferWrapper = new ArrayList<PlayerTransferWrapper>();
        int ranking = 0;
        Boolean isNearRetirementAge = false;
        for (PlayerModel player : playersToBeTransferred) {
            ranking = 0;
            ranking += Math.floor(computeRankBasedOnDefenderSkills(player.skills) * 0.5);
            ranking += computeRankBasedOnPlayerMetrics(player, ranking);
            isNearRetirementAge = checkIfRetirementAge(player);
            playerTransferWrapper.add(new PlayerTransferWrapper(ranking, player, isNearRetirementAge));
        }
        return playerTransferWrapper;
    }

    private static int computeRankBasedOnDefenderSkills(HashMap<PlayerAttributes, Integer> playerAttributesMap) {
        int ranking = 0;
        List<PlayerAttributes> playerAttributes = getDefenderRankingFactorsBasedOnSkills();
        for (PlayerAttributes attributes: playerAttributesMap.keySet()) {
            if (playerAttributes.contains(attributes)) {
                ranking += playerAttributesMap.get(attributes);
            }
        }
        return ranking;
    }

    private static int computeRankBasedOnPlayerMetrics(PlayerModel player, int ranking) {
        ranking += Math.floor((player.overall * 0.8) + (player.potential * 1.2));
        return ranking;
    }

    private static Boolean checkIfRetirementAge(PlayerModel player) {
        return player.age >= 35;
    }

    private static List<PlayerAttributes> getDefenderRankingFactorsBasedOnSkills() {
        List<PlayerAttributes> defenderAttributes = new ArrayList<PlayerAttributes>();
        defenderAttributes.add(PlayerAttributes.DEFENDING);
        defenderAttributes.add(PlayerAttributes.DEFENDING_SLIDING_TACKLE);
        defenderAttributes.add(PlayerAttributes.SKILL_LONG_PASSING);
        defenderAttributes.add(PlayerAttributes.DEFENDING_STANDING_TACKLE);
        defenderAttributes.add(PlayerAttributes.DEFENDING_MARKING_AWARENESS);
        defenderAttributes.add(PlayerAttributes.DRIBBLING);
        defenderAttributes.add(PlayerAttributes.SKILL_DRIBBLING);
        defenderAttributes.add(PlayerAttributes.PHYSIC);
        defenderAttributes.add(PlayerAttributes.POWER_STAMINA);
        defenderAttributes.add(PlayerAttributes.POWER_STRENGTH);
        defenderAttributes.add(PlayerAttributes.POWER_LONG_SHOTS);
        defenderAttributes.add(PlayerAttributes.MOVEMENT_SPRINT_SPEED);
        defenderAttributes.add(PlayerAttributes.MOVEMENT_ACCELERATION);
        defenderAttributes.add(PlayerAttributes.MOVEMENT_AGILITY);
        defenderAttributes.add(PlayerAttributes.MOVEMENT_BALANCE);
        defenderAttributes.add(PlayerAttributes.DRIBBLING);
        defenderAttributes.add(PlayerAttributes.PASSING);
        defenderAttributes.add(PlayerAttributes.PACE);
        defenderAttributes.add(PlayerAttributes.MENTALITY_COMPOSURE);
        defenderAttributes.add(PlayerAttributes.MENTALITY_AGGRESSION);
        defenderAttributes.add(PlayerAttributes.MENTALITY_INTERCEPTIONS);
        defenderAttributes.add(PlayerAttributes.MENTALITY_PENALTIES);
        defenderAttributes.add(PlayerAttributes.MENTALITY_VISION);
        defenderAttributes.add(PlayerAttributes.MENTALITY_POSITIONING);
        return defenderAttributes;
    }

//    private static int computeRankBasedOnAgeOfPlayer(PlayerModel player, int ranking) {
//        if (player.age > 35) {
//            if (player.overall <= 80) {
//                ranking -= 160;
//            }
//        } else if (player.getWeight() > 90) {
//            if (player.overall <= 85) {
//                ranking -= 150;
//            }
//        } else if (player.age > 25 && player.age <= 30) {
//            if (player.getWeight() <= 90) {
//                ranking -= 120;
//            } else {
//                ranking -= 140;
//            }
//        } else if (player.age > 25){
//            ranking -= 15;
//        }
//        return ranking;
//    }
}
