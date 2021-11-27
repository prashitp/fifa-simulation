package com.gameplay.TeamSelection.FormationSelection;

import com.models.ClubModel;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import com.models.gameplay.TeamSelection.DefaultFormations;
import java.util.*;
/**
 * @author prashitpatel
 */
public class FormationSelectionController implements IFormationSelectionController{
	ClubModel club;
	ClubModel opposingClub;

	public FormationSelectionController(ClubModel club,ClubModel opposingClub) {
		this.club = club;
		this.opposingClub = opposingClub;
	}

	public FormationModel getFormation() {
		FormationType formationType = getFormationType();
//		List<FormationModel> formations = Arrays.stream(DefaultFormations.getFormations()).filter(formation -> formation.type.equals(formationType)).toList();
		List<FormationModel> formations = new ArrayList<>();
		for (FormationModel formationModel:DefaultFormations.getFormations()) {
			if(formationModel.type.equals(formationType)) {
				formations.add(formationModel);
			}
		}

		Random rand = new Random();
		int randomFormation = rand.nextInt(formations.size());
		return formations.get(randomFormation);
	}

	private FormationType getFormationType() {
		FormationType formationType;
		HashMap.Entry<FormationType, Integer> maxEntry = null;

		HashMap<FormationType,Integer> types = evaluateCriteria();

		for (HashMap.Entry<FormationType, Integer> entry : types.entrySet())
		{
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			{
				maxEntry = entry;
			}
		}

		if(maxEntry.getValue() == 1) {
			formationType = FormationType.Neutral;
		} else {
			formationType = maxEntry.getKey();
		}
		return formationType;
	}

	private HashMap<FormationType,Integer> evaluateCriteria() {
		HashMap<FormationType,Integer> types = new HashMap<>();

		IFormationCriteriaCategory overallCriteria = new ClubOverallCriteria();
		FormationType overallCriteriaFormation = overallCriteria.getResults(club,opposingClub);
		types.put(overallCriteriaFormation,types.get(overallCriteriaFormation) != null ? types.get(overallCriteriaFormation)+1 : 1);

		IFormationCriteriaCategory individualStrengthCriteria = new ClubIndividualStrengthCriteria();
		FormationType individualStrength = individualStrengthCriteria.getResults(club,opposingClub);
		types.put(individualStrength,types.get(individualStrength) != null ? types.get(individualStrength)+1 : 1);

		IFormationCriteriaCategory relativeStrengthCriteria = new ClubIndividualStrengthCriteria();
		FormationType relativeStrength = relativeStrengthCriteria.getResults(club,opposingClub);
		types.put(relativeStrength,types.get(relativeStrength) != null ? types.get(relativeStrength)+1 : 1);

		return types;
	}
}
