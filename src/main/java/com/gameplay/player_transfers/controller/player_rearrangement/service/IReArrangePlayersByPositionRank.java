package com.gameplay.player_transfers.controller.player_rearrangement.service;
/**
 * @author: mayanksareen
 */
import com.models.PlayerTransferWrapper;
import java.util.List;

public interface IReArrangePlayersByPositionRank {
    public List<PlayerTransferWrapper> movePlayersByComputedRank(List<PlayerTransferWrapper> players);
}
