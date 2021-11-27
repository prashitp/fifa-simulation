package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.List;
/**
 * @author prashitpatel
 */
public class MidfielderCategory implements IPlayerCategory{

	PlayerSelectionService playerSelectionService = new PlayerSelectionService();
	List<PlayerModel> midfielders;

	public MidfielderCategory(List<PlayerModel> players) {
		this.midfielders = playerSelectionService.groupPlayers(players,Constants.midfielderPositions);
	}

	public void selectPlayers(int required) {
		midfielders = playerSelectionService.getMatchReadyPlayers(midfielders, Constants.MATCH_READY_STAMINA_MIDFIELDERS);
		midfielders = playerSelectionService.sortPlayers(midfielders);
		PlayerSelectionService playerSelectionServiceInstance = PlayerSelectionService.getInstance();
		playerSelectionServiceInstance.selectSquadPlayers(midfielders, required, PlayingPosition.MIDFIEDLER);
	}
}
