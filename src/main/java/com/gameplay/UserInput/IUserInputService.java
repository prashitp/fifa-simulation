package com.gameplay.UserInput;

/**
 * @author Jay Patel
 */
public interface IUserInputService {

	public Integer selectTeamId();
	
	public Boolean selectPlayingXI(Integer teamId);
	
	public Boolean customizePlayingXI();
	
	public Boolean startNewGame();
}
