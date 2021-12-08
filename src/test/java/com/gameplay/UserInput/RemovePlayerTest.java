package com.gameplay.UserInput;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.TeamSelection.TeamSelectionController;
import com.gameplay.entity.PlayerEntity;
import com.gameplay.repository.PlayerStatusRepository;
import com.io.IErrorStream;
import com.io.IInputStream;
import com.io.IOutputStream;
import com.io.StandardErrorStream;
import com.io.StandardOutputStream;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
public class RemovePlayerTest {

	private IOutputStream outputStreamMock = StandardOutputStream.getInstance();
	private IInputStream inputStreamMock = Mockito.mock(IInputStream.class);
	private IErrorStream errorStreamMock = StandardErrorStream.getInstance();
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	private RemovePlayer removePlayer = new RemovePlayer();

	@BeforeEach
	public void init() throws Exception {

		FieldSetter outputStreamNew = new FieldSetter(outputStreamMock,
				StandardOutputStream.class.getDeclaredField("printStream"));
		outputStreamNew.set(new PrintStream(outputStreamCaptor));

		FieldSetter outputStream = new FieldSetter(removePlayer, RemovePlayer.class.getDeclaredField("outputStream"));
		outputStream.set(outputStreamMock);

		FieldSetter inputStream = new FieldSetter(removePlayer, RemovePlayer.class.getDeclaredField("inputStream"));
		inputStream.set(inputStreamMock);

		FieldSetter errorStreamNew = new FieldSetter(errorStreamMock,
				StandardErrorStream.class.getDeclaredField("printStream"));
		errorStreamNew.set(new PrintStream(outputStreamCaptor));

		FieldSetter errorStream = new FieldSetter(removePlayer, RemovePlayer.class.getDeclaredField("errorStream"));
		errorStream.set(errorStreamMock);
	}

	@Test
	public void executeSelectionTest() {
		TeamSelectionController teamSelectionController = new TeamSelectionController("Liverpool", "Manchester United");
		PlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();
		List<PlayerEntity> players = teamSelectionController.getSquads().get(0).getPlaying11().entrySet().stream()
				.map(entrySet -> playerStatusRepository.fetchPlayer(entrySet.getKey().getPlayerId()))
				.collect(Collectors.toList());
		Mockito.when(inputStreamMock.readInteger())
				.thenReturn(Converter.convertToPlayerIdInteger(players.get(0).getPlayerId()));
		assertTrue(removePlayer.executeSelection(new ArrayList<>(Arrays.asList(players.get(0))), players),
				"addPlayer() method is not working as expected");
	}

	@Test
	public void executeSelectionInvalidPlayerTest() {
		TeamSelectionController teamSelectionController = new TeamSelectionController("Liverpool", "Manchester United");
		PlayerStatusRepository playerStatusRepository = new PlayerStatusRepository();
		List<PlayerEntity> players = teamSelectionController.getSquads().get(0).getPlaying11().entrySet().stream()
				.map(entrySet -> playerStatusRepository.fetchPlayer(entrySet.getKey().getPlayerId()))
				.collect(Collectors.toList());
		Mockito.when(inputStreamMock.readInteger()).thenReturn(1);
		assertFalse(removePlayer.executeSelection(new ArrayList<>(Arrays.asList(players.get(0))), players),
				"removePlayer() method is not working as expected");
	}
}
