package com.gameplay.UserInput.validation;

import com.gameplay.entity.PlayerEntity;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface ITeamValidation {

	public Boolean isTeamValid(List<PlayerEntity> players);
	
}
