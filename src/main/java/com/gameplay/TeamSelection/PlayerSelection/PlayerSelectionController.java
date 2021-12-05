package com.gameplay.TeamSelection.PlayerSelection;

import com.models.PlayingPosition;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import com.models.PlayerModel;
import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public class PlayerSelectionController implements IPlayerSelectionController{
	List<PlayerModel> players;

	int totalForwardsRequired;
	int totalMidfieldersRequired;
	int totalDefendersRequired;
	int totalGoalkeepersRequired = 1;

	FormationType formationType;

	public PlayerSelectionController(List<PlayerModel> players, FormationModel formationModel) {
		this.players=players;

		List<Integer> formation = formationModel.getFormation();
		this.formationType = formationModel.type;
		this.totalDefendersRequired = formation.get(0);
		this.totalMidfieldersRequired = formation.size() == 4 ? formation.get(1)+formation.get(2) : formation.get(1);
		this.totalForwardsRequired = formation.size() == 4 ? formation.get(3) : formation.get(2);
	}

	public HashMap<PlayerModel, PlayingPosition> getSquad() {
		PlayerSelectionService.resetplaying11();
		IPlayerCategory forwards = new ForwardCategory(players);
		IPlayerCategory midfielders = new MidfielderCategory(players);
		IPlayerCategory defenders = new DefenderCategory(players);
		IPlayerCategory goalkeepers = new GoalkeeperCategory(players);

		//give priority for player selection based on formation type
		if(formationType.equals(FormationType.DEFENSIVE)) {
			defenders.selectPlayers(totalDefendersRequired);
			midfielders.selectPlayers(totalMidfieldersRequired);
			forwards.selectPlayers(totalForwardsRequired);
		} else if(formationType.equals(FormationType.NEUTRAL)) {
			midfielders.selectPlayers(totalMidfieldersRequired);
			forwards.selectPlayers(totalForwardsRequired);
			defenders.selectPlayers(totalDefendersRequired);
		} else {
			forwards.selectPlayers(totalForwardsRequired);
			midfielders.selectPlayers(totalMidfieldersRequired);
			defenders.selectPlayers(totalDefendersRequired);
		}
		goalkeepers.selectPlayers(totalGoalkeepersRequired);

		//get final playing 11
		PlayerSelectionService playerSelectionService = PlayerSelectionService.getInstance();
		return playerSelectionService.getplaying11();
	}
}
