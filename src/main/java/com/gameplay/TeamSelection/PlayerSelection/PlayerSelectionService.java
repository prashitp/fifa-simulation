package com.gameplay.TeamSelection.PlayerSelection;

import com.models.PlayerAttributes;
import com.models.PlayerModel;
import com.models.PlayerPositions;
import com.models.PlayingPosition;

import java.util.*;
/**
 * @author prashitpatel
 */
public class PlayerSelectionService implements IPlayerSelectionService {
	static PlayerSelectionService instance = null;
	private static HashMap<PlayerModel,PlayingPosition> playing11 = new HashMap<>();

	public static PlayerSelectionService getInstance() {
		if(instance == null) {
			return new PlayerSelectionService();
		}
		return instance;
	}

	public List<PlayerModel> groupPlayers(List<PlayerModel> players, PlayerPositions[] categoryPositions) {
		List<PlayerModel> groupedPlayers = new ArrayList<>();
		for (PlayerModel player:players) {
			for (PlayerPositions position : player.getPositions()) {
				if (Arrays.asList(categoryPositions).contains(position)) {
					if (!groupedPlayers.contains(player)) {
						groupedPlayers.add(player);
					}
				}
			}
		}
		return groupedPlayers;
	}

	public List<PlayerModel> getMatchReadyPlayers(List<PlayerModel> players, int stamina) {
		List<PlayerModel> matchReadyPlayers = new ArrayList<>();
		for (PlayerModel player: players) {
			if(player.skills.get(PlayerAttributes.PowerStamina) > stamina) {
				matchReadyPlayers.add(player);
			}
		}
		return matchReadyPlayers;
	}

	public List<PlayerModel> sortPlayers(List<PlayerModel> players) {
		players.sort((p1, p2) -> (p2.overall - p1.overall));
		return players;
	}

	public void selectSquadPlayers(List<PlayerModel> players, int maxPositions, PlayingPosition category) {
		int index = 0;
		Iterator playerIterator = players.iterator();
		while (index < maxPositions && playerIterator.hasNext()) {
			PlayerModel nextPlayer =(PlayerModel) playerIterator.next();
			if(!playing11.containsKey(nextPlayer)){
				playing11.put(nextPlayer, category);
				index++;
			}
		}
	}

	public HashMap<PlayerModel, PlayingPosition> getplaying11() {
		return playing11;
	}

	public static void resetplaying11() {
		playing11 = new HashMap<>();
	}
}
