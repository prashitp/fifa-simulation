package com.gameplay.UserInput.validation;

import com.gameplay.controller.ITeamSelectionController;
import com.gameplay.controller.TeamSelectionController;
import com.gameplay.entity.PlayerEntity;
import com.gameplay.repository.IPlayerStatusRepository;
import com.gameplay.repository.PlayerStatusRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jay Patel
 */
public class TeamValidationTest {

	private IPlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();
	private static final String clubName = "Liverpool";
	private static final String opposingClubName = "Manchester United";
	private ITeamSelectionController teamSelectionController = new TeamSelectionController(clubName, opposingClubName);

	private TeamValidation teamValidation = new TeamValidation();
	
	@Test
	public void nullParameterTest() {
		assertFalse(teamValidation.isTeamValid(null),
				"isTeamValid() method is not working as expected for null parameter.");
	}

	@Test
	public void teamSizeLessThanActualSizeTest() {
		assertFalse(teamValidation.isTeamValid(Arrays.asList()),
				"isTeamValid() method is not working as expected for input size less than the actual team size.");
	}

	@Test
	public void teamSizeGreaterThanActualSizeTest() {
		assertFalse(teamValidation.isTeamValid(playerStatusRepository.fetchAllPlayers(1)),
				"isTeamValid() method is not working as expected for input size greater than the actual team size.");
	}

	@Test
	public void validTeamTest() {
		List<PlayerEntity> selectedPlayerList = teamSelectionController.getSquads().get(0).getPlaying11().entrySet()
				.stream().map(entrySet -> playerStatusRepository.fetchPlayer(entrySet.getKey().getPlayerId()))
				.collect(Collectors.toList());
		assertTrue(teamValidation.isTeamValid(selectedPlayerList),
				"isTeamValid() method is not working as expected for valid team.");
	}
}
