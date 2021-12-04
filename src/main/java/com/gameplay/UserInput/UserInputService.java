package com.gameplay.UserInput;

import com.io.IErrorStream;
import com.io.IInputStream;
import com.io.IOutputStream;
import com.io.StandardErrorStream;
import com.io.StandardInputStream;
import com.io.StandardOutputStream;

/**
 * @author Jay Patel
 */
public class UserInputService implements IUserInputService {

	private ITeamService teamService;

	private IPlayerStatusService playerStatusService;

	private IUserTeamService userTeamService;

	private IUserPlayersService userPlayersService;

	private IOutputStream outputStream;

	private IInputStream inputStream;

	private IErrorStream errorStream;

	public UserInputService() {
		teamService = new TeamService();
		playerStatusService = new PlayerStatusService();
		userTeamService = new UserTeamService();
		userPlayersService = new UserPlayersService();
		outputStream = StandardOutputStream.getInstance();
		inputStream = StandardInputStream.getInstance();
		errorStream = StandardErrorStream.getInstance();
	}

	@Override
	public Integer selectTeamId() {
		return null;
	}

	@Override
	public Boolean selectPlayingXI(Integer teamId) {
		return Boolean.FALSE;
	}

	@Override
	public Boolean customizePlayingXI() {
		return null;
	}

	@Override
	public Boolean startNewGame() {
		return null;
	}
}