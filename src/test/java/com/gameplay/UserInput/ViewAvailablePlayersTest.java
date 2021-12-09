package com.gameplay.UserInput;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.controller.TeamSelectionController;
import com.gameplay.entity.PlayerEntity;
import com.gameplay.repository.PlayerStatusRepository;
import com.io.IOutputStream;
import com.io.StandardOutputStream;

/**
 * @author Jay Patel
 */
public class ViewAvailablePlayersTest {

	private IOutputStream outputStreamMock = StandardOutputStream.getInstance();
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	private ViewAvailablePlayers viewAvailablePlayers = new ViewAvailablePlayers();

	@BeforeEach
	public void init() throws Exception {

		FieldSetter outputStreamNew = new FieldSetter(outputStreamMock,
				StandardOutputStream.class.getDeclaredField("printStream"));
		outputStreamNew.set(new PrintStream(outputStreamCaptor));

		FieldSetter outputStream = new FieldSetter(viewAvailablePlayers,
				ViewAvailablePlayers.class.getDeclaredField("outputStream"));
		outputStream.set(outputStreamMock);
	}

	@Test
	public void executeSelectionTest() {
		TeamSelectionController teamSelectionController = new TeamSelectionController("Liverpool", "Manchester United");
		PlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();
		List<PlayerEntity> players = teamSelectionController.getSquads().get(0).getPlaying11().entrySet().stream()
				.map(entrySet -> playerStatusRepository.fetchPlayer(entrySet.getKey().getPlayerId()))
				.collect(Collectors.toList());
		assertTrue(viewAvailablePlayers.executeSelection(Arrays.asList(players.get(0)), players),
				"viewAvailablePlayers() method is not working as expected");
	}
}
