package com.gameplay.TeamSelection.PlayerSelection;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;

/**
 * @author prashitpatel
 */
public interface IPlayerSelectionController {
	//return final squad
	HashMap<PlayerModel, PlayingPosition> getSquad();
}
