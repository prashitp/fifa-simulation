package com.gameplay.TeamSelection;

import com.Constants;
import com.gameplay.TeamSelection.FormationSelection.FormationSelectionController;
import com.gameplay.TeamSelection.FormationSelection.IFormationSelectionController;
import com.gameplay.TeamSelection.PlayerSelection.IPlayerSelectionController;
import com.gameplay.TeamSelection.PlayerSelection.PlayerSelectionController;
import com.models.*;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.Lineup;

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

	public Lineup getTeam() {
		//getFormation
		IFormationSelectionController formationSelectionController = new FormationSelectionController(clubDetails,opposingClubDetails);
		FormationModel formationModel = formationSelectionController.getFormation();

		//get playing11
		IPlayerSelectionController playerSelectionController = new PlayerSelectionController(Arrays.asList(clubDetails.getPlayers()),formationModel);
		return new Lineup(formationModel, playerSelectionController.getSquad());
	}
}