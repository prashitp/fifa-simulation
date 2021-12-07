package com.gameplay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.gameplay.repository.IScheduleRepository;
import com.gameplay.repository.ScheduleRepository;
import com.models.ClubModel;
import com.models.MatchModel;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
public class ScheduleService implements IScheduleService {

	private static Integer COUNT_TEAM = 20;
	private static Integer INF = Integer.MAX_VALUE;

	private Map<String, Integer> matchPlayedByTeam;
	private ITeamService teamService;
	private List<MatchModel> combinationOfMatches;
	private List<Boolean> isMatchPlayed;
	private IScheduleRepository scheduleRepository;

	public ScheduleService() {
		teamService = new TeamService();
		matchPlayedByTeam = new TreeMap<>();
		combinationOfMatches = new ArrayList<>();
		isMatchPlayed = new ArrayList<>();
		scheduleRepository = new ScheduleRepository();
	}

	@Override
	public Boolean createMatchSchedule() {
		return Boolean.FALSE;
	}
}
