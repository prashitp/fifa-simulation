package com.gameplay.controller;

import com.models.Lineup;
import java.util.List;

/**
 * @author prashitpatel
 */
public interface ITeamSelectionController {
	//get squads for both teams
	List<Lineup> getSquads();
}
