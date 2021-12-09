package com.gameplay.repository;

import com.models.MatchModel;

import java.util.List;

/**
 * @author Jay Patel
 */
public interface IScheduleRepository {

	public Boolean deleteSchedule();
	
	public Boolean saveSchedule(List<MatchModel> matches);	
}
