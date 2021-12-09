package com.models.gameplay.player_transfers;

import com.Constants;
import com.models.ClubModel;
import com.models.PlayerModel;
import com.models.PlayerPositions;
import com.models.PlayingPosition;
import com.models.gameplay.player_transfers.defenders.DefenderPlayerTransfers;
import com.models.gameplay.player_transfers.defenders.IDefenderPlayerTransfers;
import com.models.gameplay.player_transfers.forwards.ForwardPlayerTransfers;
import com.models.gameplay.player_transfers.goalkeeper.GoalKeeperPlayerTransfers;
import com.models.gameplay.player_transfers.forwards.IForwardPlayerTransfers;
import com.models.gameplay.player_transfers.goalkeeper.IGoalKeeperPlayerTransfers;
import com.models.gameplay.player_transfers.midfielders.IMidFielderPlayerTransfers;
import com.models.gameplay.player_transfers.midfielders.MidFielderPlayerTransfers;
import com.models.gameplay.player_transfers.player_rearrangement.IReArrangePlayersByClubRanks;
import com.models.gameplay.player_transfers.player_rearrangement.ReArrangePlayersByClubRanks;

import java.util.*;

/**
 * @author Mayank Sareen
 */
public class TransferService {
    private List<PlayerModel> allPlayers;
    private List<PlayerModel> forwards;
    private List<PlayerModel> defenders;
    private List<PlayerModel> goalKeeper;
    private List<PlayerModel> midFielder;
    IForwardPlayerTransfers iForwardPlayerTransfers;
    IMidFielderPlayerTransfers iMidFielderPlayerTransfers;
    IDefenderPlayerTransfers iDefenderPlayerTransfers;
    IGoalKeeperPlayerTransfers iGoalKeeperPlayerTransfers;
    IReArrangePlayersByClubRanks iReArrangePlayersByClubRanks;

    private static TransferService transferService;
    public static TransferService getInstance(List<PlayerModel> playerList) {
        if (transferService == null) {
            transferService = new TransferService(playerList);
        }
        return transferService;
    }

    private TransferService(List<PlayerModel> playerList) {
        this.allPlayers = playerList;
        filterPlayersByPositions();
    }

    private void filterPlayersByPositions() {
        HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap = new HashMap<>();
        for (ClubModel club: Constants.CLUBS) {
            HashMap<String, List<PlayerTransferWrapper>> transfersMap = new HashMap<>();
            filterPlayersByForwardPositions(club.getClubName(), transfersMap);
            filterPlayersByMidFielderPositions(club.getClubName(),transfersMap);
            filterPlayersByDefenderPositions(club.getClubName(), transfersMap);
            filterPlayersByGoalKeeperPositions(club.getClubName(), transfersMap);
            transferPlayerMap.put(club.getClubName(), transfersMap);
        }
        iReArrangePlayersByClubRanks = ReArrangePlayersByClubRanks.getInstance();
        transferPlayerMap = iReArrangePlayersByClubRanks.reArrangeClubToPlayerMappings(transferPlayerMap);
        computeBestSellingPlayers(transferPlayerMap);
    }

    private void computeBestSellingPlayers(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        computeBestSellingForwards(transferPlayerMap);
//        computeBestSellingForwards(transferPlayerMap);
//        computeBestSellingForwards(transferPlayerMap);
//        computeBestSellingForwards(transferPlayerMap);
    }

    private void computeBestSellingForwards(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        iForwardPlayerTransfers = ForwardPlayerTransfers.getInstance();
        List<PlayerModel> forwardsToBeTransferred = iForwardPlayerTransfers.computeBestSellingForwards(transferPlayerMap);
        System.out.println(forwardsToBeTransferred.size());
    }

    private HashMap<String, List<PlayerTransferWrapper>> filterPlayersByForwardPositions(String team, HashMap<String, List<PlayerTransferWrapper>> transfersMap) {
        iForwardPlayerTransfers = ForwardPlayerTransfers.getInstance();
        forwards = filterPlayersByPosition(Constants.FORWARD_POSITIONS, team);
        List<PlayerModel> playersWithExpiringContract = getPlayersWithExpiringContract(forwards);
        List<PlayerModel> forwardsListForTransfer = getPlayersWithLessGames(playersWithExpiringContract, forwards);
        transfersMap.put(PlayingPosition.FORWARD.name(), iForwardPlayerTransfers.getForwardsForTransferPerTeam(forwardsListForTransfer));
        return transfersMap;
    }

    private HashMap<String, List<PlayerTransferWrapper>> filterPlayersByMidFielderPositions(String team, HashMap<String, List<PlayerTransferWrapper>> transfersMap) {
        iMidFielderPlayerTransfers = MidFielderPlayerTransfers.getInstance();
        midFielder = filterPlayersByPosition(Constants.MIDFIELDER_POSITIONS, team);
        List<PlayerModel> playersWithExpiringContract = getPlayersWithExpiringContract(midFielder);
        List<PlayerModel> midFielderListForTransfer = getPlayersWithLessGames(playersWithExpiringContract, midFielder);
        transfersMap.put(PlayingPosition.MIDFIELDER.name(), iMidFielderPlayerTransfers.getMidFieldersForTransferPerTeam(midFielderListForTransfer));
        return transfersMap;
    }

    private HashMap<String, List<PlayerTransferWrapper>> filterPlayersByDefenderPositions(String team, HashMap<String, List<PlayerTransferWrapper>> transfersMap) {
        iDefenderPlayerTransfers = DefenderPlayerTransfers.getInstance();
        defenders = filterPlayersByPosition(Constants.DEFENDER_POSITIONS, team);
        List<PlayerModel> playersWithExpiringContract = getPlayersWithExpiringContract(defenders);
        List<PlayerModel> defenderListForTransfer = getPlayersWithLessGames(playersWithExpiringContract, defenders);
        transfersMap.put(PlayingPosition.DEFENDER.name(), iDefenderPlayerTransfers.getDefendersForTransferPerTeam(defenderListForTransfer));
        return transfersMap;
    }

    private HashMap<String, List<PlayerTransferWrapper>> filterPlayersByGoalKeeperPositions(String team, HashMap<String, List<PlayerTransferWrapper>> transfersMap) {
        iGoalKeeperPlayerTransfers = GoalKeeperPlayerTransfers.getInstance();
        goalKeeper = filterPlayersByPosition(Constants.GOALKEEPER_POSITIONS, team);
        List<PlayerModel> playersWithExpiringContract = getPlayersWithExpiringContract(goalKeeper);
        List<PlayerModel> goalKeeperListForTransfer = getPlayersWithLessGames(playersWithExpiringContract, goalKeeper);
        transfersMap.put(PlayingPosition.DEFENDER.name(), iGoalKeeperPlayerTransfers.getGoalKeeperForTransferPerTeam(goalKeeperListForTransfer));
        return transfersMap;
    }

    private List<PlayerModel> filterPlayersByPosition(PlayerPositions[] playingPosition, String team) {
        Set<PlayerModel> filteredPlayers = new HashSet<>();
        for (PlayerModel player : this.allPlayers) {
            if (player.club.equals(team)) {
                for (PlayerPositions position : player.getPositions()) {
                    if (Arrays.asList(playingPosition).contains(position)) {
                        filteredPlayers.add(player);
                    }
                }
            }
        }
        return List.copyOf(filteredPlayers);
    }

    private List<PlayerModel> getPlayersWithLessGames(List<PlayerModel> playersWithExpiringContract, List<PlayerModel> players) {
        List<PlayerModel> playersToBeTransferred = playersWithExpiringContract;
        for (PlayerModel player: players) {
            if (player.numberOfGamesPlayed < Constants.PLAYER_TRANSFER_GAMES_PLAYED_THRESHOLD
                    && !playersWithExpiringContract.contains(player)) {
                playersToBeTransferred.add(player);
            }
        }
        return playersToBeTransferred;
    }

    private List<PlayerModel> getPlayersWithExpiringContract(List<PlayerModel> players) {
        int currentYear = getCurrentYear();
        List<PlayerModel> playersWithExpiringContract = new ArrayList<PlayerModel>();
        for (PlayerModel player : players) {
            if (player.clubContractUntilYear <= currentYear + 1) {
                playersWithExpiringContract.add(player);
            }
        }
        return playersWithExpiringContract;
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
