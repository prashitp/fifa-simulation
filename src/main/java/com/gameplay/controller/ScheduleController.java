package com.gameplay.controller;

import com.gameplay.service.IScheduleService;
import com.gameplay.service.ScheduleService;
import com.models.MatchModel;

import java.util.List;

/**
 * @author Jay Patel
 */
public class ScheduleController implements IScheduleController {

	private IScheduleService scheduleService;
	
	public ScheduleController() {
		scheduleService = new ScheduleService();
	}
	
	@Override
	public List<MatchModel> createMatchSchedule() {
		return scheduleService.createMatchSchedule();
	}

}
