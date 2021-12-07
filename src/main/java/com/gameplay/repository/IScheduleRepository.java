package com.gameplay.repository;

import java.util.List;

import com.models.MatchModel;

/**
 * @author Jay Patel
 */
public interface IScheduleRepository {

	public Boolean deleteSchedule();
	
	public Boolean saveSchedule(List<MatchModel> matches);	
}
