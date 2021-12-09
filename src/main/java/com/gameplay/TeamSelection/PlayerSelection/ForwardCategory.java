package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import com.gameplay.service.PlayerSelectionService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.List;
/**
 * @author prashitpatel
 */
public class ForwardCategory implements IPlayerCategory{
	PlayerSelectionService playerSelectionService = new PlayerSelectionService();
	List<PlayerModel> forwards;

	public ForwardCategory(List<PlayerModel> players) {
		this.forwards = playerSelectionService.groupPlayers(players,Constants.FORWARD_POSITIONS);
	}

	public void selectPlayers(int required) {
		forwards = playerSelectionService.getMatchReadyPlayers(forwards, Constants.MATCH_READY_STAMINA_FORWARDS);
		forwards = playerSelectionService.sortPlayers(forwards);
		PlayerSelectionService playerSelectionServiceInstance = PlayerSelectionService.getInstance();
		playerSelectionServiceInstance.selectSquadPlayers(forwards, required, PlayingPosition.FORWARD);
	}
}
