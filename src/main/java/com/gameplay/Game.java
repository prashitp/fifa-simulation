package com.gameplay;

import java.util.*;
import java.util.stream.Collectors;

import com.gameplay.UserInput.IUserInputFunction;
import com.gameplay.UserInput.UserInputFunction;
import com.gameplay.controller.CardsController;
import com.gameplay.controller.ICardsController;
import com.gameplay.controller.IInjuryController;
import com.gameplay.controller.IPlayerTrainingController;
import com.gameplay.controller.IScheduleController;
import com.gameplay.controller.IScoreLineController;
import com.gameplay.controller.ISetPieceController;
import com.gameplay.controller.ITeamSelectionController;
import com.gameplay.controller.IUserTeamController;
import com.gameplay.controller.InjuryController;
import com.gameplay.controller.PlayerTrainingController;
import com.gameplay.controller.ScheduleController;
import com.gameplay.controller.ScoreLineController;
import com.gameplay.controller.SetPieceController;
import com.gameplay.controller.TeamSelectionController;
import com.gameplay.controller.UserTeamController;
import com.gameplay.service.GameService;
import com.gameplay.service.IGameService;
import com.io.FileOutputStream;
import com.io.IInputStream;
import com.io.IOutputStream;
import com.io.StandardInputStream;
import com.io.StandardOutputStream;
import com.models.ClubModel;
import com.models.Goal;
import com.models.Lineup;
import com.models.MatchModel;
import com.models.PlayerModel;
import com.models.SetPieceType;
import com.models.gameplay.CardType;
import com.utils.Constants;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
public class Game implements IGame {

	private IUserInputFunction userInputFunction;

	private IScheduleController scheduleController;

	private IOutputStream outputStream;

	private IOutputStream standardOutput;

	private IInputStream inputStream;

	private IPlayerTrainingController playerTrainingController;

	private IUserTeamController userTeamController;

	private static final String OUTPUT_DIRECTORY = "./output/";
	private static final String OUTPUT_FILE_PREFIX = "season_";
	private static final String OUTPUT_FILE_SUFFIX = ".txt";

	public Game() {
		userInputFunction = new UserInputFunction();
		scheduleController = new ScheduleController();
		playerTrainingController = new PlayerTrainingController();
		userTeamController = new UserTeamController();
		inputStream = StandardInputStream.getInstance();
		standardOutput = StandardOutputStream.getInstance();
	}

	@Override
	public void startGame() {

		// User Input
		userInputFunction.teamSelection();

		Integer seasonPlayed = userTeamController.fetchUserTeamModel().getSeasonPlayed();

		for (int index = seasonPlayed + 1; index <= Constants.SEASON_COUNT; index++) {

			outputStream = new FileOutputStream(OUTPUT_DIRECTORY, OUTPUT_FILE_PREFIX + index + OUTPUT_FILE_SUFFIX);

			// Create Schedule
			standardOutput.println("Preparing schedule ... ");
			List<MatchModel> matches = scheduleController.createMatchSchedule();
			standardOutput.println("Schedule created.");

			standardOutput.println("Simulating ... ");

			outputStream.println(String.format("***** SEASON %d *****", index));
			outputStream.println("");

			int matchIndex = 1;

			for (MatchModel match : matches) {

				outputStream.println(String.format("***** MATCH %d *****", matchIndex));
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

				outputStream.println(
						String.format("***** %s -vs- %s *****", homeClub.getClubName(), awayClub.getClubName()));
				outputStream.println("");

				// Playing 11
				ITeamSelectionController teamSelectionController = new TeamSelectionController(homeClub, awayClub);
				List<Lineup> lineups = teamSelectionController.getSquads();

				outputStream.println(String.format("Lineup: %s - Formation: %s", homeClub.getClubName(),
						lineups.get(0).getFormation().toString()));

				for (PlayerModel player : lineups.get(0).getPlaying11().keySet()) {
					outputStream.print(String.format("%s ", player.getPlayerName()));
				}
				outputStream.println("");
				outputStream.println("");

				outputStream.println(String.format("Lineup: %s - Formation: %s", awayClub.getClubName(),
						lineups.get(1).getFormation().toString()));

				for (PlayerModel player : lineups.get(1).getPlaying11().keySet()) {
					outputStream.print(String.format("%s ", player.getPlayerName()));
				}
				outputStream.println("");
				outputStream.println("");

				// Set Pieces
				ISetPieceController setPieceController = new SetPieceController(lineups.get(0).getPlaying11(),
						lineups.get(1).getPlaying11());
				HashMap<SetPieceType, List<Integer>> setPieces = setPieceController.getSetPieces();
				for(SetPieceType setPieceType: setPieces.keySet()){
					outputStream.print(setPieceType + ": ");
					outputStream.print(lineups.get(0).club.getClubName() + "-" + setPieces.get(setPieceType).get(0));
					outputStream.print(lineups.get(1).club.getClubName() + "-" + setPieces.get(setPieceType).get(1));
				}

				outputStream.println("");
				outputStream.println("");

				// Cards
				ICardsController cardsController = new CardsController(lineups.get(0).getPlaying11(),
						lineups.get(1).getPlaying11());
				HashMap<CardType, List<PlayerModel>> cards = cardsController.fetchFouls();
				outputStream.println("***** Fouls ******");
				for(CardType card: cards.keySet()){
					outputStream.print(card + " - ");
					for (PlayerModel player: cards.get(card)){
						outputStream.print(player.getPlayerName());
					}
				}

				outputStream.println("");
				outputStream.println("");

				// Injuries
				IInjuryController injuryController = new InjuryController(lineups.get(0).getPlaying11(),
						lineups.get(1).getPlaying11());
				HashMap<PlayerModel, Integer> injuries = injuryController.getInjuredPlayers();

				outputStream.println("***** Injured Players ******");
				for(Map.Entry<PlayerModel, Integer> injury: injuries.entrySet()){
					outputStream.print(String.format("%s - %s matches, ",injury.getKey(), injury.getValue()));
				}

				outputStream.println("");
				outputStream.println("");

				// substitution

				// Final Scores
				IScoreLineController scoreLineController = new ScoreLineController(homeClub, awayClub, lineups,
						setPieces);
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
			standardOutput.println(String.format("***** Statistics *****"));
			IGameService gameService = new GameService();
			HashMap<PlayerModel, Integer> highestGoalsPlayer = gameService.getHighestGoalScorer();

			PlayerModel highestScorer = highestGoalsPlayer.keySet().stream().collect(Collectors.toList()).get(0);
			int maxGoals = highestGoalsPlayer.get(highestScorer);
			outputStream.println(String.format("Most goals: %s - %d",highestScorer.getPlayerName(),
					maxGoals));
			standardOutput.println(String.format("Most goals: %s - %d",highestScorer.getPlayerName(),
					maxGoals));

			HashMap<ClubModel, Integer> highestGoalsClub = gameService.getHighestGoalsByClub();
			ClubModel highestScoringClub = highestGoalsClub.keySet().stream().collect(Collectors.toList()).get(0);
			int maxGoalsClub = highestGoalsClub.get(highestScoringClub);
			outputStream.println(String.format("Most goals: %s - %d",highestScoringClub.getClubName(),
					maxGoalsClub));
			standardOutput.println(String.format("Most goals: %s - %d\n",highestScoringClub.getClubName(),
					maxGoalsClub));

			gameService.resetPlayerGoals();
			gameService.resetClubGoals();

			List<ClubModel> clubsForPointsTable = Arrays.asList(Constants.CLUBS);
			clubsForPointsTable.sort((c1, c2) -> (c2.points - c1.points));
			StringBuilder pointsTable = new StringBuilder();
			String headerFormat = "| %-8S | %-30S |    %-4s |    %-4s |    %-4s |    %-4s |   %-8S |\n";
			String rowFormat = "|    %-5S | %-30S |    %-4s |    %-4s |    %-4s |    %-4s |     %-7S|\n";
			int tableWidth = 96;
			pointsTable.append("+" + "-".repeat(tableWidth)).append("+\n");
			pointsTable.append(String.format(headerFormat, "Position", "Club", "P", "W", "D", "L", "Points"));
			pointsTable.append("+" + "-".repeat(tableWidth)).append("+\n");
			int rank = 1;
			for (ClubModel club : clubsForPointsTable) {
				pointsTable.append(String.format(rowFormat, rank++,
						club.getClubName(), club.matchesPlayed, club.matchesWin, club.matchesLoss,
						club.matchesDraw, club.points));
			}

			pointsTable.append("+" + "-".repeat(tableWidth)).append("+\n");
			outputStream.println(pointsTable);
			standardOutput.println(pointsTable);
			//transfer

			standardOutput.println(String.format("Simulation completed for season %d", index));

			// update database with number of season played
			userTeamController.setSeasonPlayed(index);

			if (index < Constants.SEASON_COUNT) {
				standardOutput.println(
						"Type EXIT if you don't want to simulate next season. Otherwise write anything except EXIT: ");
				String option = inputStream.readLine();
				if (option.equalsIgnoreCase("exit")) {
					break;
				}
			}
		}
	}
}
