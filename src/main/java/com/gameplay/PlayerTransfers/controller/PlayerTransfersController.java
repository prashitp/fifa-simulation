package com.gameplay.PlayerTransfers.controller;
/**
 * @author mayanksareen
 */
import com.gameplay.PlayerTransfers.PlayerRearrangement.service.IReArrangePlayersByClubRanks;
import com.gameplay.PlayerTransfers.PlayerRearrangement.service.ReArrangePlayersByClubRanks;
import com.models.*;
import com.utils.Constants;
import java.util.*;

public class PlayerTransfersController {
    public static final int PLAYER_TRANSFER_GAMES_PLAYED_THRESHOLD = 3;
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

    private static PlayerTransfersController transferService;
    public static PlayerTransfersController getInstance(List<PlayerModel> playerList) {
        if (transferService == null) {
            transferService = new PlayerTransfersController(playerList);
        }
        return transferService;
    }

    private PlayerTransfersController(List<PlayerModel> playerList) {
        this.allPlayers = playerList;
    }

    public List<String> filterPlayersByPositions() {
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
        return computeBestSellingPlayers(transferPlayerMap);
    }

    private List<String> computeBestSellingPlayers(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<String> output = new ArrayList<>();
        iForwardPlayerTransfers = ForwardPlayerTransfers.getInstance();
        List<PlayerModel> forwardsToBeTransferred = iForwardPlayerTransfers.computeBestSellingForwards(transferPlayerMap);
        for (PlayerModel player: forwardsToBeTransferred) {
            output.add(" " + player.getPlayerName() + " transferred to " + player.club);
        }
        iDefenderPlayerTransfers = DefenderPlayerTransfers.getInstance();
        List<PlayerModel> defendersToBeTransferred = iDefenderPlayerTransfers.computeBestSellingDefenders(transferPlayerMap);
        for (PlayerModel player: defendersToBeTransferred) {
           output.add(" " + player.getPlayerName() + " transferred to " + player.club);
        }
        iGoalKeeperPlayerTransfers = GoalKeeperPlayerTransfers.getInstance();
        List<PlayerModel> goaliesToBeTransferred = iGoalKeeperPlayerTransfers.computeBestSellingGoalies(transferPlayerMap);
        for (PlayerModel player: goaliesToBeTransferred) {
            output.add(" "+ player.getPlayerName() + " transferred to " + player.club);
        }
        iMidFielderPlayerTransfers = MidFielderPlayerTransfers.getInstance();
        List<PlayerModel> midFieldersToBeTransferred = iMidFielderPlayerTransfers.computeBestSellingMidFields(transferPlayerMap);
        for (PlayerModel player: midFieldersToBeTransferred) {
           output.add(" "+player.getPlayerName() + " transferred to " + player.club);
        }
       return output;
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
            if (player.numberOfGamesPlayed < PLAYER_TRANSFER_GAMES_PLAYED_THRESHOLD
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
