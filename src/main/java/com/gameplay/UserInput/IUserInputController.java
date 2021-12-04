package com.gameplay.UserInput;

/**
 * @author Jay Patel
 */
public interface IUserInputController {

	public Integer selectTeamId();
	
	public void selectPlayingXI(Integer teamId);
	
	public Boolean customizePlayingXI();
	
	public Boolean startNewGame();
}
