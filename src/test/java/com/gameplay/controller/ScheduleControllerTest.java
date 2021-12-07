package com.gameplay.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.gameplay.service.IScheduleService;
import com.gameplay.service.ScheduleService;

public class ScheduleControllerTest {

	private IScheduleController scheduleController = new ScheduleController();
	private IScheduleService scheduleServiceMock = Mockito.mock(ScheduleService.class);

	@BeforeEach
	public void init() throws Exception {
		FieldSetter scheduleService = new FieldSetter(scheduleController,
				ScheduleController.class.getDeclaredField("scheduleService"));
		scheduleService.set(scheduleServiceMock);
	}

	@Test
	public void createMatchScheduleTest() {
		Mockito.when(scheduleServiceMock.createMatchSchedule()).thenReturn(Boolean.TRUE);
		assertTrue(scheduleController.createMatchSchedule(),
				"createMatchSchedule() method is not working as expected.");
	}
}
