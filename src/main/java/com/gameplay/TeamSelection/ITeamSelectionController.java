package com.gameplay.TeamSelection;

import com.models.gameplay.TeamSelection.Lineup;
import java.util.List;

/**
 * @author prashitpatel
 */
public interface ITeamSelectionController {
	//get squads for both teams
	List<Lineup> getSquads();
}
