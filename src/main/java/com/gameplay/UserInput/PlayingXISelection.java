package com.gameplay.UserInput;

import com.gameplay.entity.PlayerEntity;

import java.util.List;

/**
 * @author Jay Patel
 */
public abstract class PlayingXISelection {

	public abstract Boolean executeSelection(List<PlayerEntity> selectedPlayers, List<PlayerEntity> availablePlayers);

}
