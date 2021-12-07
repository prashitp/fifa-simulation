package com.gameplay.UserInput;

import java.util.List;

import com.gameplay.entity.PlayerEntity;

public abstract class PlayingXISelection {

	public abstract Boolean executeSelection(List<PlayerEntity> selectedPlayers, List<PlayerEntity> availablePlayers);

}
