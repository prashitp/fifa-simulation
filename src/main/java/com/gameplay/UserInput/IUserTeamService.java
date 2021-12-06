package com.gameplay.UserInput;

import com.models.UserTeamModel;

/**
 * @author Jay Patel
 */
public interface IUserTeamService {

	public UserTeamModel getUserTeam();
	
	public Boolean setUserTeam(Integer teamId);

	public Boolean customizePlayingXI(Boolean flag);
	
	public Boolean setSeasonPlayed(Integer seasonPlayed);
	
	public Boolean deleteUserTeam();
}
