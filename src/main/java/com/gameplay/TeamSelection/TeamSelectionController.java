package com.gameplay.TeamSelection;

import com.Constants;
import com.gameplay.TeamSelection.FormationSelection.FormationSelectionController;
import com.gameplay.TeamSelection.FormationSelection.IFormationSelectionController;
import com.gameplay.TeamSelection.PlayerSelection.IPlayerSelectionController;
import com.gameplay.TeamSelection.PlayerSelection.PlayerSelectionController;
import com.models.*;
import com.models.FormationModel;
import com.models.Lineup;
import java.util.*;

/**
 * @author prashitpatel
 */
public class TeamSelectionController implements ITeamSelectionController {
	ClubModel clubDetails;
	ClubModel opposingClubDetails;
	HashMap<PlayerModel,String > playing11 = new HashMap<>();

	public TeamSelectionController(String clubName, String opposingClubName) {
		//get club details
		clubDetails = Arrays.stream(Constants.CLUBS).filter(club -> club.getClubName().equals(clubName)).toArray(ClubModel[]::new)[0];
		opposingClubDetails = Arrays.stream(Constants.CLUBS).filter(club -> club.getClubName().equals(opposingClubName)).toArray(ClubModel[]::new)[0];
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