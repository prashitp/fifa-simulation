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
		return Boolean.FALSE;
	}
}
