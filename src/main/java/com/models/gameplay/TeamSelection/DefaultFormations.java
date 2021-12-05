package com.models.gameplay.TeamSelection;

/**
 * @author prashitpatel
 */
public class DefaultFormations {
	private static FormationModel[] formations;

	public static FormationModel[] getFormations() {

		formations = new FormationModel[]{
				new FormationModel(4, 4, 2, FormationType.NEUTRAL),
				new FormationModel(3, 5, 2, FormationType.NEUTRAL),
				new FormationModel(5, 3, 2, FormationType.NEUTRAL),
				new FormationModel(4, 2, 2,2, FormationType.NEUTRAL),
				new FormationModel(4, 3, 2, 1, FormationType.NEUTRAL),
				new FormationModel(3, 4, 1,2, FormationType.NEUTRAL),
				new FormationModel(3, 4, 3, FormationType.ATTACKING),
				new FormationModel(4, 3, 3, FormationType.ATTACKING),
				new FormationModel(3, 2,3, 2, FormationType.ATTACKING),
				new FormationModel(4, 1,4, 1, FormationType.ATTACKING),
				new FormationModel(3, 4,1, 2, FormationType.ATTACKING),
				new FormationModel(4, 1,3, 2, FormationType.ATTACKING),
				new FormationModel(4, 2,3, 1, FormationType.ATTACKING),
				new FormationModel(5, 4, 1, FormationType.DEFENSIVE),
				new FormationModel(5, 3, 2, FormationType.DEFENSIVE),
				new FormationModel(4, 5, 1, FormationType.DEFENSIVE),
				new FormationModel(4, 4,1, 1, FormationType.DEFENSIVE),
				new FormationModel(5, 1,3, 1, FormationType.DEFENSIVE),
				new FormationModel(5, 3,1, 1, FormationType.DEFENSIVE),
				new FormationModel(4, 3,1, 2, FormationType.DEFENSIVE),
				new FormationModel(4, 3,2, 1, FormationType.DEFENSIVE),
		};
		return formations;
	}
}
