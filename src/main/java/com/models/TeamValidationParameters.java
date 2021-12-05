package com.models;

/**
 * @author Jay Patel
 */
public class TeamValidationParameters {
	
	private Integer curPlayerIndex;
	
	private Integer teamSize;
	
	private Integer numberOfDefenders;
	
	private Integer numberOfMidFielders;
	
	private Integer numberOfForwards;
	
	private Integer numberOfGoalKeep;
	
	public Integer getCurPlayerIndex() {
		return curPlayerIndex;
	}

	public Integer getTeamSize() {
		return teamSize;
	}

	public Integer getNumberOfDefenders() {
		return numberOfDefenders;
	}

	public Integer getNumberOfMidFielders() {
		return numberOfMidFielders;
	}

	public Integer getNumberOfForwards() {
		return numberOfForwards;
	}

	public Integer getNumberOfGoalKeep() {
		return numberOfGoalKeep;
	}

	public static class Builder {
		
		private TeamValidationParameters parameters;
		
		public Builder() {
			parameters = new TeamValidationParameters();
		}
		
		public Builder setCurPlayerIndex(Integer curPlayerIndex) {
			parameters.curPlayerIndex = curPlayerIndex;
			return this;
		}

		public Builder setTeamSize(Integer teamSize) {
			parameters.teamSize = teamSize;
			return this;
		}
		
		public Builder setNumberOfDefenders(Integer numberOfDefenders) {
			parameters.numberOfDefenders = numberOfDefenders;
			return this;
		}

		public Builder setNumberOfMidFielders(Integer numberOfMidFielders) {
			parameters.numberOfMidFielders = numberOfMidFielders;
			return this;
		}

		public Builder setNumberOfForwards(Integer numberOfForwards) {
			parameters.numberOfForwards = numberOfForwards;
			return this;
		}

		public Builder setNumberOfGoalKeep(Integer numberOfGoalKeep) {
			parameters.numberOfGoalKeep = numberOfGoalKeep;
			return this;
		}
		
		public TeamValidationParameters getTeamValidationParameters() {
			return this.parameters;
		}
	}
}
