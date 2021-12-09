package com.gameplay.PlayerTransfers.controller;
/**
 * @author: mayanksareen
 */
import com.gameplay.PlayerTransfers.PlayerRearrangement.service.*;
import com.models.*;
import com.utils.CommonFunctions;
import com.utils.Constants;

import java.util.*;

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

    @Override
    public List<PlayerModel> computeBestSellingMidFields(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper>playersReady = getTopMidFieldersInTheGameAcrossClubs(transferPlayerMap);
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
        HashMap<ClubModel, PlayerModel> topTransfers = tradeTopFourMidFieldersBetweenTopFourClubs(highestPayingClubs, players);
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

    private HashMap<ClubModel, PlayerModel> tradeTopFourMidFieldersBetweenTopFourClubs(List<ClubModel> clubs, List<PlayerTransferWrapper> players) {
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

    private List<PlayerTransferWrapper> getTopMidFieldersInTheGameAcrossClubs(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper> topMidFieldersAcrossAllClubs = new ArrayList<>();
        for (String club : transferPlayerMap.keySet()) {
            if (transferPlayerMap.get(club).get(PlayingPosition.MIDFIELDER.name()) != null) {
                topMidFieldersAcrossAllClubs.addAll(transferPlayerMap.get(club).get(PlayingPosition.MIDFIELDER.name()));
            }
        }
       if (topMidFieldersAcrossAllClubs.size() > 0) {
            shiftPlayers.movePlayersByComputedRank(topMidFieldersAcrossAllClubs);
        }
        return topMidFieldersAcrossAllClubs;
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
            ranking += Math.floor(computeRankBasedOnMidFielderSkills(player.skills) * 0.5);
            ranking += computeRankBasedOnPlayerMetrics(player, ranking);
            isNearRetirementAge = checkIfRetirementAge(player);
            playerTransferWrapper.add(new PlayerTransferWrapper(ranking, player, isNearRetirementAge));
        }
        return playerTransferWrapper;
    }

    private static int computeRankBasedOnMidFielderSkills(HashMap<PlayerAttributes, Integer> playerAttributesMap) {
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
}
