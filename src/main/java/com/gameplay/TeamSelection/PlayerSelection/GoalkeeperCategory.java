package com.gameplay.TeamSelection.PlayerSelection;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.List;
/**
 * @author prashitpatel
 */
public class GoalkeeperCategory implements IPlayerCategory{
	PlayerSelectionService playerSelectionService = new PlayerSelectionService();
	List<PlayerModel> goalkeepers;

	public GoalkeeperCategory(List<PlayerModel> players) {
		this.goalkeepers = playerSelectionService.groupPlayers(players,Constants.goalkeeperPositions);
	}

	public void selectPlayers(int required) {
		goalkeepers = playerSelectionService.getMatchReadyPlayers(goalkeepers, Constants.MATCH_READY_STAMINA_GOALKEEPER);
		goalkeepers = playerSelectionService.sortPlayers(goalkeepers);
		PlayerSelectionService playerSelectionServiceInstance = PlayerSelectionService.getInstance();
		playerSelectionServiceInstance.selectSquadPlayers(goalkeepers, required, PlayingPosition.GOALKEEPER);
	}
}
