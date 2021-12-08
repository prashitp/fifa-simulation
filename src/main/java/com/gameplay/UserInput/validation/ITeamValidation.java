package com.gameplay.UserInput.validation;

import java.util.List;

import com.gameplay.entity.PlayerEntity;

/**
 * @author Jay Patel
 */
public interface ITeamValidation {

	public Boolean isTeamValid(List<PlayerEntity> players);
	
}
