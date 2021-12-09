package com.gameplay.TeamSelection.PlayerSelection;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.List;

/**
 * @author prashitpatel
 */
public class PlayerCategoryFactory {
	public IPlayerCategory getPlayerCategory(PlayingPosition playingPosition, List<PlayerModel> players) {
		if(playingPosition.equals(PlayingPosition.FORWARD)) {
			return new ForwardCategory(players);
		} else if (playingPosition.equals(PlayingPosition.DEFENDER)) {
			return new DefenderCategory(players);
		} else if (playingPosition.equals(PlayingPosition.MIDFIELDER)) {
			return new MidfielderCategory(players);
		} else {
			return new GoalkeeperCategory(players);
		}

	}
}
