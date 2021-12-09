package com.models.gameplay.player_transfers.goalkeeper;

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

public class GoalKeeperPlayerTransfers implements IGoalKeeperPlayerTransfers {
    private static GoalKeeperPlayerTransfers goalKeeperTransfers;
    IReArrangePlayersByPositionRank shiftPlayers = new ReArrangePlayersByRank();
    public static GoalKeeperPlayerTransfers getInstance() {
        if (goalKeeperTransfers == null) {
            goalKeeperTransfers = new GoalKeeperPlayerTransfers();
        }
        return goalKeeperTransfers;
    }

    @Override
    public List<PlayerTransferWrapper> getGoalKeeperForTransferPerTeam(List<PlayerModel> goalKeeperListForTransfers) {
        List<PlayerTransferWrapper> rankedGoalKeeperForTransfer = getTopGoalKeeperForTransfer(goalKeeperListForTransfers);
        shiftPlayers.movePlayersByComputedRank(rankedGoalKeeperForTransfer);
        return rankedGoalKeeperForTransfer;
    }

    private List<PlayerTransferWrapper> getTopGoalKeeperForTransfer(List<PlayerModel>playersWithExpiringContract) {
        List<PlayerTransferWrapper> topGoalKeepers = computeRanking(playersWithExpiringContract);
        return topGoalKeepers;
    }

    private List<PlayerTransferWrapper> computeRanking(List<PlayerModel> playersToBeTransferred) {
        List<PlayerTransferWrapper> playerTransferWrapper = new ArrayList<PlayerTransferWrapper>();
        int ranking = 0;
        Boolean isNearRetirementAge = false;
        for (PlayerModel player : playersToBeTransferred) {
            ranking = 0;
            ranking += Math.floor(computeRankBasedOnGoalKeeperSkills(player.skills) * 0.5);
            ranking += computeRankBasedOnPlayerMetrics(player, ranking);
            isNearRetirementAge = checkIfRetirementAge(player);
            playerTransferWrapper.add(new PlayerTransferWrapper(ranking, player, isNearRetirementAge));
        }
        return playerTransferWrapper;
    }

    private static int computeRankBasedOnGoalKeeperSkills(HashMap<PlayerAttributes, Integer> playerAttributesMap) {
        int ranking = 0;
        List<PlayerAttributes> playerAttributes = getForwardRankingFactorsBasedOnSkills();
        for (PlayerAttributes attributes : playerAttributesMap.keySet()) {
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

    private static List<PlayerAttributes> getForwardRankingFactorsBasedOnSkills() {
        List<PlayerAttributes> goalKeeperAttributes = new ArrayList<PlayerAttributes>();
        goalKeeperAttributes.add(PlayerAttributes.GOALKEEPING_DIVING);
        goalKeeperAttributes.add(PlayerAttributes.GOALKEEPING_HANDLING);
        goalKeeperAttributes.add(PlayerAttributes.SKILL_LONG_PASSING);
        goalKeeperAttributes.add(PlayerAttributes.GOALKEEPING_KICKING);
        goalKeeperAttributes.add(PlayerAttributes.GOALKEEPING_POSITIONING);
        goalKeeperAttributes.add(PlayerAttributes.GOALKEEPING_REFLEXES);
        goalKeeperAttributes.add(PlayerAttributes.GOALKEEPING_SPEED);
        goalKeeperAttributes.add(PlayerAttributes.PHYSIC);
        goalKeeperAttributes.add(PlayerAttributes.POWER_STAMINA);
        goalKeeperAttributes.add(PlayerAttributes.POWER_STRENGTH);
        goalKeeperAttributes.add(PlayerAttributes.POWER_LONG_SHOTS);
        goalKeeperAttributes.add(PlayerAttributes.MOVEMENT_SPRINT_SPEED);
        goalKeeperAttributes.add(PlayerAttributes.MOVEMENT_ACCELERATION);
        goalKeeperAttributes.add(PlayerAttributes.MOVEMENT_AGILITY);
        goalKeeperAttributes.add(PlayerAttributes.MOVEMENT_BALANCE);
        goalKeeperAttributes.add(PlayerAttributes.DRIBBLING);
        goalKeeperAttributes.add(PlayerAttributes.PASSING);
        goalKeeperAttributes.add(PlayerAttributes.PACE);
        goalKeeperAttributes.add(PlayerAttributes.MENTALITY_COMPOSURE);
        goalKeeperAttributes.add(PlayerAttributes.MENTALITY_AGGRESSION);
        goalKeeperAttributes.add(PlayerAttributes.MENTALITY_INTERCEPTIONS);
        goalKeeperAttributes.add(PlayerAttributes.MENTALITY_PENALTIES);
        goalKeeperAttributes.add(PlayerAttributes.MENTALITY_VISION);
        goalKeeperAttributes.add(PlayerAttributes.MENTALITY_POSITIONING);
        return goalKeeperAttributes;
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
