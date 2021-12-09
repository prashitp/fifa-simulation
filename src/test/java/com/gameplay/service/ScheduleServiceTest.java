package com.gameplay.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.database_operations.DatabaseImport;
import com.gameplay.repository.IScheduleRepository;
import com.gameplay.repository.ScheduleRepository;
import com.models.MatchModel;

/**
 * @author Jay Patel
 */
public class ScheduleServiceTest {

	private IScheduleService scheduleService = new ScheduleService();
	private ITeamService teamServiceMock = Mockito.mock(TeamService.class);
	private IScheduleRepository scheduleRepositoryMock = Mockito.mock(ScheduleRepository.class);

	@Test
	public void createMatchScheduleTest() throws Exception {
		MatchModel match = new MatchModel("C01", "C02");

		FieldSetter scheduleRepository = new FieldSetter(scheduleService,
				ScheduleService.class.getDeclaredField("scheduleRepository"));
		scheduleRepository.set(scheduleRepositoryMock);

		FieldSetter teamService = new FieldSetter(scheduleService,
				ScheduleService.class.getDeclaredField("teamService"));
		teamService.set(teamServiceMock);

		Mockito.when(teamServiceMock.fetchAllTeams()).thenReturn(Arrays.asList(DatabaseImport.getInstance().getClubs()),
				Arrays.asList(DatabaseImport.getInstance().getClubs()));
		Mockito.when(scheduleRepositoryMock.deleteSchedule()).thenReturn(Boolean.TRUE);
		Mockito.when(scheduleRepositoryMock.saveSchedule(Arrays.asList(match))).thenReturn(Boolean.TRUE);
		assertTrue(scheduleService.createMatchSchedule() != null, "createMatchSchedule() method is not working as expected.");
	}
}
