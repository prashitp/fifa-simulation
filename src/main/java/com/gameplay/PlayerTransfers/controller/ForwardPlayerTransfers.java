package com.gameplay.PlayerTransfers.controller;
/**
 * @author: mayanksareen
 */
import com.gameplay.PlayerTransfers.PlayerRearrangement.service.*;
import com.models.*;
import com.utils.CommonFunctions;
import com.utils.Constants;

import java.util.*;

public class ForwardPlayerTransfers implements IForwardPlayerTransfers {
    private static ForwardPlayerTransfers forwardPlayerTransfers;
    IReArrangePlayersByPositionRank shiftPlayers = new ReArrangePlayersByRank();
    public static ForwardPlayerTransfers getInstance() {
        if (forwardPlayerTransfers == null) {
            forwardPlayerTransfers = new ForwardPlayerTransfers();
        }
        return forwardPlayerTransfers;
    }

    @Override
    public List<PlayerTransferWrapper> getForwardsForTransferPerTeam(List<PlayerModel> forwardsListForTransfer) {
        List<PlayerTransferWrapper> rankedForwardsForTransfer = getTopForwardsForTransferWithinAClub(forwardsListForTransfer);
        shiftPlayers.movePlayersByComputedRank(rankedForwardsForTransfer);
        return rankedForwardsForTransfer;
    }
    @Override
    public  List<PlayerModel> computeBestSellingForwards(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper>playersReady = getTopForwardsInTheGameAcrossClubs(transferPlayerMap);
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
        HashMap<ClubModel, PlayerModel> topTransfers = tradeTopFourForwardsBetweenTopFourClubs(highestPayingClubs, players);
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

    private HashMap<ClubModel, PlayerModel> tradeTopFourForwardsBetweenTopFourClubs(List<ClubModel> clubs, List<PlayerTransferWrapper> players) {
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

    private List<PlayerTransferWrapper> getTopForwardsInTheGameAcrossClubs(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<PlayerTransferWrapper> topForwardsAcrossAllClubs = new ArrayList<>();
        for (String club : transferPlayerMap.keySet()) {
            if (transferPlayerMap.get(club).get(PlayingPosition.FORWARD.name()) != null) {
                topForwardsAcrossAllClubs.addAll(transferPlayerMap.get(club).get(PlayingPosition.FORWARD.name()));
            }
        }
        if (topForwardsAcrossAllClubs.size() > 0) {
            shiftPlayers.movePlayersByComputedRank(topForwardsAcrossAllClubs);
        }
        return topForwardsAcrossAllClubs;
    }

    private List<PlayerTransferWrapper> getTopForwardsForTransferWithinAClub(List<PlayerModel>playersWithExpiringContract) {
        List<PlayerTransferWrapper> topForwards = computeRanking(playersWithExpiringContract);
        return topForwards;
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
        List<PlayerAttributes> playerAttributes = getForwardRankingFactorsBasedOnSkills();
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

    private static List<PlayerAttributes> getForwardRankingFactorsBasedOnSkills() {
        List<PlayerAttributes> forwardAttributes = new ArrayList<PlayerAttributes>();
        forwardAttributes.add(PlayerAttributes.SHOOTING);
        forwardAttributes.add(PlayerAttributes.SKILL_CURVE);
        forwardAttributes.add(PlayerAttributes.SKILL_LONG_PASSING);
        forwardAttributes.add(PlayerAttributes.ATTACKING_VOLLEYS);
        forwardAttributes.add(PlayerAttributes.ATTACKING_HEADING_ACCURACY);
        forwardAttributes.add(PlayerAttributes.ATTACKING_FINISHING);
        forwardAttributes.add(PlayerAttributes.ATTACKING_CROSSING);
        forwardAttributes.add(PlayerAttributes.ATTACKING_SHORT_PASSING);
        forwardAttributes.add(PlayerAttributes.POWER_STAMINA);
        forwardAttributes.add(PlayerAttributes.POWER_STRENGTH);
        forwardAttributes.add(PlayerAttributes.POWER_LONG_SHOTS);
        forwardAttributes.add(PlayerAttributes.MOVEMENT_SPRINT_SPEED);
        forwardAttributes.add(PlayerAttributes.MOVEMENT_ACCELERATION);
        forwardAttributes.add(PlayerAttributes.MOVEMENT_AGILITY);
        forwardAttributes.add(PlayerAttributes.MOVEMENT_BALANCE);
        forwardAttributes.add(PlayerAttributes.DRIBBLING);
        forwardAttributes.add(PlayerAttributes.PASSING);
        forwardAttributes.add(PlayerAttributes.PACE);
        forwardAttributes.add(PlayerAttributes.MENTALITY_COMPOSURE);
        forwardAttributes.add(PlayerAttributes.MENTALITY_AGGRESSION);
        forwardAttributes.add(PlayerAttributes.MENTALITY_INTERCEPTIONS);
        forwardAttributes.add(PlayerAttributes.MENTALITY_PENALTIES);
        forwardAttributes.add(PlayerAttributes.MENTALITY_VISION);
        forwardAttributes.add(PlayerAttributes.MENTALITY_POSITIONING);
        return forwardAttributes;
    }
}
