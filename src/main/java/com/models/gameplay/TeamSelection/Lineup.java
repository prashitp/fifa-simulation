package com.models.gameplay.TeamSelection;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;

/**
 * @author prashitpatel
 */
public class Lineup {
	private FormationModel formation;
	private HashMap<PlayerModel,PlayingPosition> playing11 = new HashMap<>();

	public Lineup(FormationModel formation, HashMap<PlayerModel,PlayingPosition> playing11) {
		this.formation = formation;
		this.playing11 = playing11;
	}

	public FormationModel getFormation() {
		return formation;
	}

	public HashMap<PlayerModel, PlayingPosition> getplaying11() {
		return playing11;
	}
}
