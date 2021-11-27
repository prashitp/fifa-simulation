package com.gameplay.TeamSelection.PlayerSelection;

import com.models.PlayerModel;
import com.models.PlayerPositions;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;
/**
 * @author prashitpatel
 */
public interface IPlayerSelectionService {

	//group players based on categories - forwards, defenders, goalkeepers
	List<PlayerModel> groupPlayers(List<PlayerModel> players, PlayerPositions[] categoryPositions);

	//get players with match ready stamina value
	List<PlayerModel> getMatchReadyPlayers(List<PlayerModel> players, int stamina);

	//sort players according to overall attribute
	List<PlayerModel> sortPlayers(List<PlayerModel> players);

	//add players to playing11 as required by formation selected
	void selectSquadPlayers(List<PlayerModel> players, int maxPositions, PlayingPosition category);

	//return playing11
	HashMap<PlayerModel, PlayingPosition> getplaying11();
}
