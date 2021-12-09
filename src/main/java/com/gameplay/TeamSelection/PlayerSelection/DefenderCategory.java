package com.gameplay.TeamSelection.PlayerSelection;

import com.utils.Constants;
import com.gameplay.service.PlayerSelectionService;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.List;
/**
 * @author prashitpatel
 */
public class DefenderCategory implements IPlayerCategory{

	PlayerSelectionService playerSelectionService = new PlayerSelectionService();
	List<PlayerModel> defenders;

	public DefenderCategory(List<PlayerModel> players) {
		this.defenders = playerSelectionService.groupPlayers(players,Constants.DEFENDER_POSITIONS);
	}

	public void selectPlayers(int required) {
		defenders = playerSelectionService.getMatchReadyPlayers(defenders, Constants.MATCH_READY_STAMINA_DEFENDERS);
		defenders = playerSelectionService.sortPlayers(defenders);
		PlayerSelectionService playerSelectionServiceInstance = PlayerSelectionService.getInstance();
		playerSelectionServiceInstance.selectSquadPlayers(defenders, required, PlayingPosition.DEFENDER);
	}
}
