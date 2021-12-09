package com.gameplay;

import com.gameplay.service.GameService;
import com.gameplay.service.IGameService;
import com.utils.Constants;
import com.gameplay.UserInput.IUserInputFunction;
import com.gameplay.UserInput.UserInputFunction;
import com.gameplay.controller.*;
import com.io.IOutputStream;
import com.io.StandardOutputStream;
import com.models.*;
import com.models.gameplay.CardType;
import com.utils.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import com.gameplay.controller.IPlayerTrainingController;
import com.gameplay.controller.IScheduleController;
import com.gameplay.controller.PlayerTrainingController;
import com.gameplay.controller.ScheduleController;
import com.models.MatchModel;

/**
 * @author Jay Patel
 */
public class Game implements IGame {

	private IUserInputFunction userInputFunction;

	private IScheduleController scheduleController;

	private IOutputStream outputStream;

	private IPlayerTrainingController playerTrainingController;

	public Game() {
		userInputFunction = new UserInputFunction();
		scheduleController = new ScheduleController();
		outputStream = StandardOutputStream.getInstance();
		playerTrainingController = new PlayerTrainingController();
	}

	@Override
	public void startGame() {
		// User Input
		userInputFunction.teamSelection();

		// Create Schedule
		List<MatchModel> matches = scheduleController.createMatchSchedule();

		for(int index = 1; index<=Constants.SEASON_COUNT; index++) {
			outputStream.println(String.format("***** SEASON %d *****",index));
			outputStream.println("");

			int matchIndex = 1;

			for(MatchModel match:matches) {

				outputStream.println(String.format("***** MATCH %d *****",matchIndex));
				outputStream.println("");

				// Start a match
				String homeId = match.getHomeTeamId();
				String awayId = match.getAwayTeamId();

				int homeIntId = Converter.convertToTeamIdInteger(homeId);
				int awayIntId = Converter.convertToTeamIdInteger(awayId);

				ClubModel homeClub = Arrays.stream(Constants.CLUBS).filter(club -> club.getClubId() == homeIntId).collect(Collectors.toList()).get(0);
				ClubModel awayClub = Arrays.stream(Constants.CLUBS).filter(club -> club.getClubId() == awayIntId).collect(Collectors.toList()).get(0);
				homeClub.matchesPlayed += 1;
				awayClub.matchesPlayed += 1;

				outputStream.println(String.format("***** %s -vs- %s *****",homeClub.getClubName(), awayClub.getClubName()));
				outputStream.println("");

				// Playing 11
				ITeamSelectionController teamSelectionController = new TeamSelectionController(homeClub,awayClub);
				List<Lineup> lineups = teamSelectionController.getSquads();

				outputStream.println(String.format("Lineup: %s - Formation: %s",homeClub.getClubName(),
						lineups.get(0).getFormation().toString()));

				for (PlayerModel player:lineups.get(0).getPlaying11().keySet()) {
					outputStream.print(String.format("%s ",player.getPlayerName()));
				}
				outputStream.println("");
				outputStream.println("");


				outputStream.println(String.format("Lineup: %s - Formation: %s",awayClub.getClubName(),
						lineups.get(1).getFormation().toString()));

				for (PlayerModel player:lineups.get(1).getPlaying11().keySet()) {
					outputStream.print(String.format("%s ",player.getPlayerName()));
				}
				outputStream.println("");
				outputStream.println("");

				// Set Pieces
				ISetPieceController setPieceController = new SetPieceController(lineups.get(0).getPlaying11(),lineups.get(1).getPlaying11());
				HashMap<SetPieceType, List<Integer>> setPieces = setPieceController.getSetPieces();

				// Cards
				ICardsController cardsController = new CardsController(lineups.get(0).getPlaying11(),lineups.get(1).getPlaying11());
				HashMap<CardType, List<PlayerModel>> cards = cardsController.fetchFouls();


				// Injuries
				IInjuryController injuryController = new InjuryController(lineups.get(0).getPlaying11(),lineups.get(1).getPlaying11());
				HashMap<PlayerModel, Integer> injuries = injuryController.getInjuredPlayers();

				//substitution

				// Final Scores
				IScoreLineController scoreLineController = new ScoreLineController(homeClub,awayClub,lineups,setPieces);
				HashMap<ClubModel, List<Goal>> scoreline = scoreLineController.getScoreLine();

				int homeGoals = 0;
				int awayGoals = 0;
				if(scoreline.containsKey(homeClub)) {
					homeGoals = scoreline.get(homeClub).size();
				}
				if(scoreline.containsKey(awayClub)) {
					awayGoals = scoreline.get(awayClub).size();
				}

				outputStream.println("***** Scores *****");
				outputStream.println(String.format("%s %d - %d %s",homeClub.getClubName(),
						homeGoals,awayGoals,awayClub.getClubName()));
				outputStream.println("");

				if(homeGoals > awayGoals) {
					homeClub.matchesWin += 1;
					awayClub.matchesLoss += 1;
					homeClub.points += 3;
				} else if(homeGoals < awayGoals){
					awayClub.matchesWin  += 1;
					homeClub.matchesLoss += 1;
					awayClub.points += 3;
				} else {
					homeClub.matchesDraw += 1;
					awayClub.matchesDraw += 1;
					homeClub.points += 1;
					awayClub.points += 1;
				}

				for (ClubModel club:scoreline.keySet()) {
					outputStream.println(String.format("%s",club.getClubName()));
					for(Goal goal:scoreline.get(club)) {
						outputStream.println(String.format("%s - %s",goal.getGoalScorer().getPlayerName(),goal.getGoalType()));
					}
					outputStream.println("");
				}

				// Training
				playerTrainingController.performTrainingSession();
				
				matchIndex++;
			}
			outputStream.println(String.format("***** Statistics *****"));
			IGameService gameService = new GameService();
			HashMap<PlayerModel, Integer> highestGoalsPlayer = gameService.getHighestGoalScorer();

			PlayerModel highestScorer = highestGoalsPlayer.keySet().stream().toList().get(0);
			int maxGoals = highestGoalsPlayer.get(highestScorer);
			outputStream.println(String.format("Most goals: %s - %d",highestScorer.getPlayerName(),
					maxGoals));

			HashMap<ClubModel, Integer> highestGoalsClub = gameService.getHighestGoalsByClub();
			ClubModel highestScoringClub = highestGoalsClub.keySet().stream().toList().get(0);
			int maxGoalsClub = highestGoalsClub.get(highestScoringClub);
			outputStream.println(String.format("Most goals: %s - %d",highestScoringClub.getClubName(),
					maxGoalsClub));

			gameService.resetPlayerGoals();
			gameService.resetClubGoals();

			List<ClubModel> clubsForPointsTable = Arrays.asList(Constants.CLUBS);
			clubsForPointsTable.sort((c1, c2) -> (c2.points - c1.points));
			for (ClubModel club: clubsForPointsTable) {
				outputStream.println(String.format("%s - P-%d, W-%d, L-%d, D-%d, Points-%d",
						club.getClubName(), club.matchesPlayed, club.matchesWin, club.matchesLoss,club.matchesDraw, club.points ));
			}
			//transfer

		}
	}
}
