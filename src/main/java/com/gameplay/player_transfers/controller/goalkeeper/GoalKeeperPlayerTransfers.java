package com.gameplay.player_transfers.controller.goalkeeper;
/**
 * @author: mayanksareen
 */
import com.gameplay.player_transfers.controller.player_rearrangement.service.*;
import com.models.*;
import com.utils.CommonFunctions;
import com.utils.Constants;

import java.util.*;

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
    @Override
    public  List<PlayerModel> computeBestSellingGoalies(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper>playersReady = getTopGoaliesInTheGameAcrossClubs(transferPlayerMap);
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
        if (players.size() > 0) {
            HashMap<ClubModel, PlayerModel> topTransfers = tradeTopFourGoaliesBetweenTopFourClubs(highestPayingClubs, players);
            List<PlayerModel> finalizedDataPostTransfers = new ArrayList<>(Constants.ALL_PLAYERS_AFTER_TRANSFER);
            Set<PlayerModel> playerTransferList = new HashSet<>();
            for (ClubModel club : topTransfers.keySet()) {
                playerTransferList.add(topTransfers.get(club));
                for (PlayerTransferWrapper player : players) {
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
        return new ArrayList<>();
    }

    private HashMap<ClubModel, PlayerModel> tradeTopFourGoaliesBetweenTopFourClubs(List<ClubModel> clubs, List<PlayerTransferWrapper> players) {
        IClubPlayerPreferenceMatching iClubPlayerPreferenceMatching = new ClubPlayerPreferenceMatching();
        ArrayList<Integer> matchedPreferences = new ArrayList<>();
        HashMap<ClubModel, PlayerModel> topTransfers = new HashMap<>();
        ArrayList<Integer> topFourClubsIndices = new ArrayList<>();
        ArrayList<ClubModel> topFourClubs = new ArrayList<>();
        ArrayList<PlayerModel> topFourPlayers = new ArrayList<>();
        ArrayList<Integer> topFourPlayersIndices = new ArrayList<>();
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

    private List<PlayerTransferWrapper> getTopGoaliesInTheGameAcrossClubs(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper> topGoaliesAcrossAllClubs = new ArrayList<>();
        for (String club : transferPlayerMap.keySet()) {
            if (transferPlayerMap.get(club).get(PlayingPosition.GOALKEEPER.name()) != null) {
                topGoaliesAcrossAllClubs.addAll(transferPlayerMap.get(club).get(PlayingPosition.GOALKEEPER.name()));
            }
        }
        if (topGoaliesAcrossAllClubs.size() > 0) {
            shiftPlayers.movePlayersByComputedRank(topGoaliesAcrossAllClubs);
        }
       return topGoaliesAcrossAllClubs;
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
        List<PlayerAttributes> playerAttributes = getGoalKeeperRankingFactorsBasedOnSkills();
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

    private static List<PlayerAttributes> getGoalKeeperRankingFactorsBasedOnSkills() {
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
}
