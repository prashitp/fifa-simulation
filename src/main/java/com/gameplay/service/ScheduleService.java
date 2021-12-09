package com.gameplay.service;

import com.gameplay.repository.IScheduleRepository;
import com.gameplay.repository.ScheduleRepository;
import com.models.ClubModel;
import com.models.MatchModel;
import com.utils.Converter;

import java.util.*;

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
	public List<MatchModel> createMatchSchedule() {
		List<MatchModel> schedule = new ArrayList<>();
		schedule.addAll(createSchedule(Boolean.TRUE));
		schedule.addAll(createSchedule(Boolean.FALSE));
		scheduleRepository.deleteSchedule();
		scheduleRepository.saveSchedule(schedule);
		return schedule;
	}

	private List<MatchModel> createSchedule(Boolean firstTeamPlayingHomeMatch) {
		combinationOfMatches = findTotalMatchesToBePlayed(firstTeamPlayingHomeMatch);
		isMatchPlayed = initializeMatchesPlayed(combinationOfMatches);
		List<MatchModel> schedule = new ArrayList<>();
		createSchedule(new TreeSet<String>(), schedule);
		return schedule;
	}

	private List<MatchModel> findTotalMatchesToBePlayed(Boolean firstTeamPlayingHomeMatch) {
		List<ClubModel> teams = new ArrayList<>(teamService.fetchAllTeams());
		Collections.shuffle(teams);
		COUNT_TEAM = teams.size();
		List<MatchModel> matches = new ArrayList<>();
		for (int i = 0; i < COUNT_TEAM; i++) {
			for (int j = i + 1; j < COUNT_TEAM; j++) {
				MatchModel match = new MatchModel();
				if (firstTeamPlayingHomeMatch) {
					match.setHomeTeamId(Converter.convertToTeamIdString(teams.get(i).getClubId()));
					match.setAwayTeamId(Converter.convertToTeamIdString(teams.get(j).getClubId()));
				} else {
					match.setHomeTeamId(Converter.convertToTeamIdString(teams.get(j).getClubId()));
					match.setAwayTeamId(Converter.convertToTeamIdString(teams.get(i).getClubId()));
				}
				matches.add(match);
			}
		}
		return matches;
	}

	private List<Boolean> initializeMatchesPlayed(List<MatchModel> matches) {
		List<Boolean> isMatchPlayed = new ArrayList<>(matches.size());
		for (int i = 0; i < matches.size(); i++) {
			isMatchPlayed.add(Boolean.FALSE);
		}
		return isMatchPlayed;
	}

	private void createSchedule(Set<String> teamIdOfPreviousMatch, List<MatchModel> schedule) {
		if (isAllMatchesScheduled()) {
			return;
		}
		int sum = INF;
		Integer matchIndex = -1;
		for (int i = 0; i < combinationOfMatches.size(); i++) {
			String homeTeamId = combinationOfMatches.get(i).getHomeTeamId();
			String awayTeamId = combinationOfMatches.get(i).getAwayTeamId();
			if (teamIdOfPreviousMatch.contains(homeTeamId) || teamIdOfPreviousMatch.contains(awayTeamId)
					|| isMatchPlayed.get(i)) {
				continue;
			}
			Integer matchesPlayedByHomeTeam = matchPlayedByTeam.getOrDefault(homeTeamId, 0);
			Integer matchesPlayedByAwayTeam = matchPlayedByTeam.getOrDefault(awayTeamId, 0);
			if (matchesPlayedByHomeTeam + matchesPlayedByAwayTeam < sum) {
				sum = matchesPlayedByHomeTeam + matchesPlayedByAwayTeam;
				matchIndex = i;
			}
		}
		assert (sum < INF);
		assert (matchIndex > -1);
		addMatch(matchIndex, schedule);
		teamIdOfPreviousMatch.clear();
		teamIdOfPreviousMatch.add(combinationOfMatches.get(matchIndex).getHomeTeamId());
		teamIdOfPreviousMatch.add(combinationOfMatches.get(matchIndex).getAwayTeamId());
		createSchedule(teamIdOfPreviousMatch, schedule);
	}

	private Boolean isAllMatchesScheduled() {
		for (int i = 0; i < combinationOfMatches.size(); i++) {
			if (isMatchPlayed.get(i).equals(Boolean.FALSE)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	private void addMatch(Integer matchIndex, List<MatchModel> schedule) {
		isMatchPlayed.set(matchIndex, Boolean.TRUE);
		String homeTeamId = combinationOfMatches.get(matchIndex).getHomeTeamId();
		String awayTeamId = combinationOfMatches.get(matchIndex).getAwayTeamId();
		matchPlayedByTeam.put(homeTeamId, matchPlayedByTeam.getOrDefault(homeTeamId, 0) + 1);
		matchPlayedByTeam.put(awayTeamId, matchPlayedByTeam.getOrDefault(awayTeamId, 0) + 1);
		schedule.add(combinationOfMatches.get(matchIndex));
	}
}
