package com.models.gameplay.player_transfers.midfielders;

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

public class MidFielderPlayerTransfers implements IMidFielderPlayerTransfers {
    private static MidFielderPlayerTransfers midFielderInstance;
    IReArrangePlayersByPositionRank shiftPlayers = new ReArrangePlayersByRank();
    public static MidFielderPlayerTransfers getInstance() {
        if (midFielderInstance == null) {
            midFielderInstance = new MidFielderPlayerTransfers();
        }
        return midFielderInstance;
    }

    @Override
    public List<PlayerTransferWrapper> getMidFieldersForTransferPerTeam(List<PlayerModel> midfielderListForTransfers) {
        List<PlayerTransferWrapper> rankedMidFieldersForTransfer = getTopMidFieldersForTransfer(midfielderListForTransfers);
        shiftPlayers.movePlayersByComputedRank(rankedMidFieldersForTransfer);
        return rankedMidFieldersForTransfer;
    }

    private List<PlayerTransferWrapper> getTopMidFieldersForTransfer(List<PlayerModel>playersWithExpiringContract) {
        List<PlayerTransferWrapper> topMidFielders = computeRanking(playersWithExpiringContract);
        return topMidFielders;
    }

    private List<PlayerTransferWrapper> computeRanking(List<PlayerModel> playersToBeTransferred) {
        List<PlayerTransferWrapper> playerTransferWrapper = new ArrayList<PlayerTransferWrapper>();
        int ranking = 0;
        Boolean isNearRetirementAge = false;
        for (PlayerModel player : playersToBeTransferred) {
            ranking = 0;
            ranking += Math.floor(computeRankBasedOnForwardSkills(player.skills) * 0.5);
            ranking += computeRankBasedOnPlayerMetrics(player, ranking);
            isNearRetirementAge = checkIfRetirementAge(player);
            playerTransferWrapper.add(new PlayerTransferWrapper(ranking, player, isNearRetirementAge));
        }
        return playerTransferWrapper;
    }

    private static int computeRankBasedOnForwardSkills(HashMap<PlayerAttributes, Integer> playerAttributesMap) {
        int ranking = 0;
        List<PlayerAttributes> playerAttributes = getMidFielderRankingFactorsBasedOnSkills();
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

    private static List<PlayerAttributes> getMidFielderRankingFactorsBasedOnSkills() {
        List<PlayerAttributes> midFielderAttributes = new ArrayList<PlayerAttributes>();
        midFielderAttributes.add(PlayerAttributes.POWER_STAMINA);
        midFielderAttributes.add(PlayerAttributes.POWER_STRENGTH);
        midFielderAttributes.add(PlayerAttributes.POWER_LONG_SHOTS);
        midFielderAttributes.add(PlayerAttributes.MOVEMENT_SPRINT_SPEED);
        midFielderAttributes.add(PlayerAttributes.MOVEMENT_ACCELERATION);
        midFielderAttributes.add(PlayerAttributes.MOVEMENT_AGILITY);
        midFielderAttributes.add(PlayerAttributes.MOVEMENT_BALANCE);
        midFielderAttributes.add(PlayerAttributes.DRIBBLING);
        midFielderAttributes.add(PlayerAttributes.PASSING);
        midFielderAttributes.add(PlayerAttributes.ATTACKING_CROSSING);
        midFielderAttributes.add(PlayerAttributes.ATTACKING_FINISHING);
        midFielderAttributes.add(PlayerAttributes.ATTACKING_SHORT_PASSING);
        midFielderAttributes.add(PlayerAttributes.PACE);
        midFielderAttributes.add(PlayerAttributes.MENTALITY_COMPOSURE);
        midFielderAttributes.add(PlayerAttributes.MENTALITY_AGGRESSION);
        midFielderAttributes.add(PlayerAttributes.MENTALITY_INTERCEPTIONS);
        midFielderAttributes.add(PlayerAttributes.MENTALITY_PENALTIES);
        midFielderAttributes.add(PlayerAttributes.MENTALITY_VISION);
        midFielderAttributes.add(PlayerAttributes.MENTALITY_POSITIONING);

        return midFielderAttributes;
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
