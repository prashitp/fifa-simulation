package com.gameplay.UserInput;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.entity.PlayerEntity;
import com.gameplay.TeamSelection.ITeamSelectionController;
import com.gameplay.TeamSelection.TeamSelectionController;

/**
 * @author Jay Patel
 */
public class TeamValidationTest {

	private IPlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();
	private static final String clubName = "Liverpool";
	private static final String opposingClubName = "Manchester United";
	private ITeamSelectionController teamSelectionController = new TeamSelectionController(clubName, opposingClubName);

	@Test
	public void nullParameterTest() {
		assertFalse(TeamValidation.isTeamValid(null),
				"isTeamValid() method is not working as expected for null parameter.");
	}

	@Test
	public void teamSizeLessThanActualSizeTest() {
		assertFalse(TeamValidation.isTeamValid(Arrays.asList()),
				"isTeamValid() method is not working as expected for input size less than the actual team size.");
	}

	@Test
	public void teamSizeGreaterThanActualSizeTest() {
		assertFalse(TeamValidation.isTeamValid(playerStatusRepository.fetchAllPlayers(1)),
				"isTeamValid() method is not working as expected for input size greater than the actual team size.");
	}

	@Test
	public void validTeamTest() {
		List<PlayerEntity> selectedPlayerList = teamSelectionController.getTeam().getplaying11().entrySet().stream()
				.map(entrySet -> playerStatusRepository.fetchPlayer(entrySet.getKey().getPlayerId()))
				.collect(Collectors.toList());
		assertTrue(TeamValidation.isTeamValid(selectedPlayerList),
				"isTeamValid() method is not working as expected for valid team.");
	}
}
