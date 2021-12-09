package com.gameplay.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import com.CommonFunctions;
import com.LogService;
import com.gameplay.entity.PlayerEntity;
import com.utils.Converter;

/**
 * @author Jay Patel
 */
public class PlayerTrainingService implements IPlayerTrainingService {

	private static final Integer NUMBER_OF_PLAYERS_FOR_EACH_TRAINING_SESSION = 2;
	private static final Integer MIN_CHANGE_IN_ATTRIBUTE = -1;
	private static final Integer MAX_CHANGE_IN_ATTRIBUTE = 1;

	private static final Integer MAX_ATTRIBUTE_VALUE = 99;
	private static final Integer MIN_ATTRIBUTE_VALUE = 50;

	private IPlayerStatusService playerStatusService;
	
	private LogService logService;

	public PlayerTrainingService() {
		playerStatusService = new PlayerStatusService();
		logService = new LogService();
	}

	@Override
	public Boolean performTrainingSession() {
		try {
			Set<Integer> selectedPlayers = new TreeSet<>();
			List<PlayerEntity> allPlayers = playerStatusService.fetchAllPlayers();

			List<PlayerEntity> players = selectPlayers(selectedPlayers, allPlayers);
			updateShooting(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updatePassing(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updateDribbling(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updatePhysic(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updateAttacking(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updateSkill(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updateMovement(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updatePower(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updateMentality(players);

			players = selectPlayers(selectedPlayers, allPlayers);
			updateDefending(players);

			return Boolean.TRUE;
		} catch (Exception e) {
			logService.log(Level.SEVERE, e.getMessage());
		}
		return Boolean.FALSE;
	}

	private List<PlayerEntity> selectPlayers(Set<Integer> selectedPlayers, List<PlayerEntity> allPlayers) {
		List<PlayerEntity> players = new ArrayList<>();
		Collections.shuffle(allPlayers);
		int idx = 0;
		while (idx < allPlayers.size() && players.size() < NUMBER_OF_PLAYERS_FOR_EACH_TRAINING_SESSION) {
			Integer playerId = Converter.convertToPlayerIdInteger(allPlayers.get(idx).getPlayerId());
			if (Boolean.FALSE.equals(selectedPlayers.contains(playerId))) {
				players.add(allPlayers.get(idx));
			}
			selectedPlayers.add(playerId);
			idx++;
		}
		return players;
	}

	private void updateShooting(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setShooting(validateValue(player.getShooting() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updateDribbling(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setDribbling(validateValue(player.getDribbling() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
	}

	private void updatePhysic(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setPhysic(validateValue(player.getPhysic() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
	}

	private void updatePassing(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setPassing(player.getPassing() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updateAttacking(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setAttackingCrossing(validateValue(player.getAttackingCrossing() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setAttackingFinishing(validateValue(player.getAttackingFinishing() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setAttackingHeadingAccuracy(validateValue(player.getAttackingHeadingAccuracy() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setAttackingShortPassing(validateValue(player.getAttackingShortPassing() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setAttackingVolleys(validateValue(player.getAttackingVolleys() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updateSkill(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setSkillBallControl(validateValue(player.getSkillBallControl() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setSkillCurve(validateValue(player.getSkillCurve() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setSkillDribbling(validateValue(player.getSkillDribbling() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setSkillFkAccuracy(validateValue(player.getSkillFkAccuracy() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setSkillLongPassing(validateValue(player.getSkillLongPassing() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setSkillMoves(validateValue(player.getSkillMoves() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updateMovement(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setMovementAcceleration(validateValue(player.getMovementAcceleration() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMovementAgility(validateValue(player.getMovementAgility() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMovementBalance(validateValue(player.getMovementBalance() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMovementReactions(validateValue(player.getMovementReactions() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMovementSprintSpeed(validateValue(player.getMovementSprintSpeed() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updatePower(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setPowerJumping(validateValue(player.getPowerJumping() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setPowerLongShots(validateValue(player.getPowerLongShots() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setPowerShotPower(validateValue(player.getPowerShotPower() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setPowerStamina(validateValue(player.getPowerStamina() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setPowerStrength(validateValue(player.getPowerStrength() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updateMentality(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setMentalityAggression(validateValue(player.getMentalityAggression() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMentalityComposure(validateValue(player.getMentalityComposure() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMentalityInterceptions(validateValue(player.getMentalityInterceptions() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMentalityPenalties(validateValue(player.getMentalityPenalties() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMentalityPositioning(validateValue(player.getMentalityPositioning() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setMentalityVision(validateValue(player.getMentalityVision() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private void updateDefending(List<PlayerEntity> players) {
		for (PlayerEntity player : players) {
			player.setDefending(validateValue(player.getDefending() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setDefendingMarkingAwareness(validateValue(player.getDefendingMarkingAwareness() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setDefendingSlidingTackle(validateValue(player.getDefendingSlidingTackle() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			player.setDefendingStandingTackle(validateValue(player.getDefendingStandingTackle() + CommonFunctions
					.generateRandomIntegerBetweenRange(MIN_CHANGE_IN_ATTRIBUTE, MAX_CHANGE_IN_ATTRIBUTE)));
			playerStatusService.savePlayer(player);
		}
		players.clear();
	}

	private Integer validateValue(Integer skill) {
		return Math.max(Math.min(skill, MAX_ATTRIBUTE_VALUE), MIN_ATTRIBUTE_VALUE);
	}
}
