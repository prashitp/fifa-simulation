package com.gameplay;

import com.utils.Constants;
import com.gameplay.UserInput.IUserInputFunction;
import com.gameplay.UserInput.UserInputFunction;
import com.gameplay.controller.*;
import com.io.IOutputStream;
import com.io.StandardOutputStream;
import com.models.*;
import com.models.gameplay.CardType;
import com.utils.Converter;

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

				outputStream.println("***** Scores *****");
//				outputStream.println(String.format("%s %d - %d %s",homeClub.getClubName(),
//						scoreline.get(homeClub).size(),scoreline.get(awayClub).size(),awayClub.getClubName()));
				outputStream.println("");

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
			//transfer
		}
	}
}
