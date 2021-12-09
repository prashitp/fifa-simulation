/**
 * @author Mayank Sareen
 */

package com.gameplay.PlayerTransfers.PlayerRearrangement.service;

import com.models.PlayerTransferWrapper;
import java.util.List;

public class ReArrangePlayersByRank implements IReArrangePlayersByPositionRank {
    @Override
    public List<PlayerTransferWrapper> movePlayersByComputedRank(List<PlayerTransferWrapper> players) {
        movePlayers(players, 0, players.size() - 1);
        players = reArrangePlayersByRank(players);
        return players;
    }

    private List<PlayerTransferWrapper> reArrangePlayersByRank(List<PlayerTransferWrapper> players) {
        if (players.size() > 1) {
            PlayerTransferWrapper lowestRankedPlayer = players.remove(0);
            reArrangePlayersByRank(players);
            players.add(lowestRankedPlayer);
        }
        return players;
    }

    private void movePlayers(List<PlayerTransferWrapper> players, int lowestPosition, int topMostPosition) {
        if (lowestPosition < topMostPosition) {
            int partRankingIndex = partPlayersByRanking(players, lowestPosition, topMostPosition);
            movePlayers(players, lowestPosition, partRankingIndex - 1);
            movePlayers(players, partRankingIndex + 1, topMostPosition);
        }
    }

    private int partPlayersByRanking(List<PlayerTransferWrapper> players, int lowestPosition, int topMostPosition) {
        int topMostRank = players.get(topMostPosition).getRank();
        int i = (lowestPosition - 1);
        for(int j = lowestPosition; j <= topMostPosition - 1; j++)
        {
            if (players.get(j).getRank() < topMostRank)
            {
                i++;
                shiftPlayer(players, i, j);
            }
        }
        shiftPlayer(players, i + 1, topMostPosition);
        return (i + 1);
    }

    private void shiftPlayer(List<PlayerTransferWrapper> players, int i, int j) {
        int positionOne = players.get(i).getRank();
        players.get(i).setRank(players.get(j).getRank());
        players.get(j).setRank(positionOne);
    }
}

