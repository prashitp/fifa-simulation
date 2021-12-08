package com.models;

import java.util.HashMap;

/**
 * @author prashitpatel
 */
public class Lineup {
	private FormationModel formation;
	private HashMap<PlayerModel,PlayingPosition> playing11;
	public ClubModel club;

	public Lineup(ClubModel club, FormationModel formation, HashMap<PlayerModel,PlayingPosition> playing11) {
		this.club = club;
		this.formation = formation;
		this.playing11 = playing11;
	}

	public FormationModel getFormation() {
		return formation;
	}

	public HashMap<PlayerModel, PlayingPosition> getPlaying11() {
		return playing11;
	}
}
