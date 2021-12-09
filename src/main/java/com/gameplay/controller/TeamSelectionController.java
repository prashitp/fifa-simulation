package com.gameplay.controller;

import com.models.ClubModel;
import com.models.FormationModel;
import com.models.Lineup;
import com.models.PlayerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author prashitpatel
 */
public class TeamSelectionController implements ITeamSelectionController {
	ClubModel clubDetails;
	ClubModel opposingClubDetails;
	HashMap<PlayerModel,String > playing11 = new HashMap<>();

	public TeamSelectionController(ClubModel clubName, ClubModel opposingClubName) {
		//get club details
		this.clubDetails = clubName;
		this.opposingClubDetails = opposingClubName;
	}

	public List<Lineup> getSquads() {
		List<Lineup> squads = new ArrayList<>();
		Lineup homeLineup = getPlaying11(clubDetails, opposingClubDetails);
		squads.add(homeLineup);

		Lineup awayLineup = getPlaying11(opposingClubDetails, clubDetails);
		squads.add(awayLineup);

		return squads;
	}

	private Lineup getPlaying11(ClubModel club, ClubModel opposingClub) {
		//getFormation
		IFormationSelectionController formationSelectionController = new FormationSelectionController(club,opposingClub);
		FormationModel formationModel = formationSelectionController.getFormation();

		//get playing11
		IPlayerSelectionController playerSelectionController = new PlayerSelectionController(Arrays.asList(club.getPlayers()),formationModel);
		return new Lineup(club, formationModel, playerSelectionController.getSquad());
	}
}