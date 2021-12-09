/**
 * @author Mayank Sareen
 */

package com.gameplay.player_transfers.defenders;

import com.CommonFunctions;
import com.Constants;
import com.gameplay.player_transfers.player_rearrangement.*;
import com.models.ClubModel;
import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;
import java.util.*;

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
    @Override
    public List<PlayerModel> computeBestSellingDefenders(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper>playersReady = getTopDefendersInTheGameAcrossClubs(transferPlayerMap);
        return transferPlayer(playersReady);
    }

    private List<PlayerModel> transferPlayer(List<PlayerTransferWrapper> players) {
        IReArrangePlayersByClubRanks reArrangePlayersByClubRanks = new ReArrangePlayersByClubRanks();
        List<ClubModel> highestPayingClubs = reArrangePlayersByClubRanks.getHighestPayingClub();
        List<PlayerTransferWrapper> filterRetiredPlayers = new ArrayList<>();
        filterRetiredPlayers.addAll(players);
        for (PlayerTransferWrapper player : filterRetiredPlayers) {
            if (player.getIsNearRetirement() && player.getPlayer().overall < 80) {
                Constants.RETIRED_PLAYERS.add(player);
                players.remove(player);
            }
        }
        HashMap<ClubModel, PlayerModel> topTransfers = tradeTopFourDefendersBetweenTopFourClubs(highestPayingClubs, players);
        List<PlayerModel> finalizedDataPostTransfers = new ArrayList<>(Constants.ALL_PLAYERS_AFTER_TRANSFER);
        Set<PlayerModel> playerTransferList = new HashSet<>();
        for (ClubModel club : topTransfers.keySet()) {
            playerTransferList.add(topTransfers.get(club));
            for(PlayerTransferWrapper player : players) {
                if (player.getPlayer().club.equals(club) && (topTransfers.get(club) != player.getPlayer())) {
                    int playerIndex = finalizedDataPostTransfers.indexOf(player.getPlayer());
                    player.getPlayer().club = highestPayingClubs.get(CommonFunctions.generateRandomIntegerBetweenRange(0, highestPayingClubs.size())).getClubName();
                    finalizedDataPostTransfers.set(playerIndex, player.getPlayer());
                    playerTransferList.add(player.getPlayer());
                }
            }
        }
        Constants.ALL_PLAYERS_AFTER_TRANSFER = finalizedDataPostTransfers;
        finalizedDataPostTransfers.clear();
        finalizedDataPostTransfers.addAll(playerTransferList);
        return finalizedDataPostTransfers;
    }

    private HashMap<ClubModel, PlayerModel> tradeTopFourDefendersBetweenTopFourClubs(List<ClubModel> clubs, List<PlayerTransferWrapper> players) {
        IClubPlayerPreferenceMatching iClubPlayerPreferenceMatching = new ClubPlayerPreferenceMatching();
        ArrayList<Integer> matchedPreferences = new ArrayList<>();
        HashMap<ClubModel, PlayerModel> topTransfers = new HashMap<>();
        ArrayList<Integer> topFourClubsIndices = new ArrayList<Integer>();
        ArrayList<ClubModel> topFourClubs = new ArrayList<>();
        ArrayList<PlayerModel> topFourPlayers = new ArrayList<>();
        ArrayList<Integer> topFourPlayersIndices = new ArrayList<Integer>();
        for (Integer i=0; i<4; i++) {
            topFourClubs.add(clubs.get(i));
            topFourClubsIndices.add(i+4);
        }

        for (Integer i=0; i<4; i++) {
            topFourPlayers.add(players.get(i).getPlayer());
            topFourPlayersIndices.add(i);
        }

        ArrayList<ArrayList<Integer>> preferenceIndices = new ArrayList<ArrayList<Integer>>();
        for (Integer i=0; i<8; i++) {
            preferenceIndices.add(new ArrayList());
            if (i < 4) {
                Collections.shuffle(topFourClubsIndices);
                preferenceIndices.get(i).addAll(topFourClubsIndices);
            } else {
                Collections.shuffle(topFourPlayersIndices);
                preferenceIndices.get(i).addAll(topFourPlayersIndices);
            }
        }

        matchedPreferences = iClubPlayerPreferenceMatching.matchClubsAndPlayersBasedOnTheirPreferences(preferenceIndices);
        for (Integer i = 0; i < 4; i++){
            topTransfers.put(topFourClubs.get(i), topFourPlayers.get(matchedPreferences.get(i)));
        }
        return topTransfers;
    }

    private List<PlayerTransferWrapper> getTopDefendersInTheGameAcrossClubs(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper> topDefendersAcrossAllClubs = new ArrayList<>();
        for (String club : transferPlayerMap.keySet()) {
            if (transferPlayerMap.get(club).get(PlayingPosition.DEFENDER.name()) != null) {
                topDefendersAcrossAllClubs.addAll(transferPlayerMap.get(club).get(PlayingPosition.DEFENDER.name()));
            }
        }
        if (topDefendersAcrossAllClubs.size() > 0) {
            shiftPlayers.movePlayersByComputedRank(topDefendersAcrossAllClubs);
        }
        return topDefendersAcrossAllClubs;
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
