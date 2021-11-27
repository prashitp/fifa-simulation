package com.gameplay.TeamSelection.FormationSelection;

import com.models.ClubModel;
import com.models.gameplay.TeamSelection.FormationType;
/**
 * @author prashitpatel
 */
public interface IFormationCriteriaCategory {
	FormationType getResults(ClubModel club, ClubModel opposingClub);
}
