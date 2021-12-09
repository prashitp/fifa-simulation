package com.gameplay.PlayerTransfers.PlayerRearrangement.service;
/**
 * @author: mayanksareen
 */
import com.models.PlayerTransferWrapper;
import java.util.List;

public interface IReArrangePlayersByPositionRank {
    public List<PlayerTransferWrapper> movePlayersByComputedRank(List<PlayerTransferWrapper> players);
}
