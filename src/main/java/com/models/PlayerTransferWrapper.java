/**
 * @author mayanksareen
 */
package com.models;

public class PlayerTransferWrapper {
    private int rank;
    private PlayerModel player;
    private Boolean isNearRetirement;

    public PlayerTransferWrapper(int rank, PlayerModel player, Boolean isNearRetirement) {
        this.rank = rank;
        this.player = player;
        this.isNearRetirement = isNearRetirement;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public Boolean getIsNearRetirement() {
        return isNearRetirement;
    }
}
