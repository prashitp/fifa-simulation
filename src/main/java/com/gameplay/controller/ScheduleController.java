package com.gameplay.controller;

import com.gameplay.service.IScheduleService;
import com.gameplay.service.ScheduleService;

/**
 * @author Jay Patel
 */
public class ScheduleController implements IScheduleController {

	private IScheduleService scheduleService;
	
	public ScheduleController() {
		scheduleService = new ScheduleService();
	}
	
	@Override
	public Boolean createMatchSchedule() {
		return scheduleService.createMatchSchedule();
	}

}
