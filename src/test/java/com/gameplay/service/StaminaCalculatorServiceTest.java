package com.gameplay.service;
/**
 * @author mayanksareen
 */

import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.utils.Constants;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaminaCalculatorServiceTest {
    HashMap<PlayerModel, PlayingPosition> team1 = new HashMap<>();

    public StaminaCalculatorServiceTest() {
        team1.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
        team1.put(Constants.PLAYERS[12], PlayingPosition.MIDFIELDER);
        team1.put(Constants.PLAYERS[20], PlayingPosition.DEFENDER);
        team1.put(Constants.PLAYERS[39], PlayingPosition.GOALKEEPER);
    }

    @Test
    void getComputedStaminaTest(){
        IStaminaCalculatorService staminaCalculatorService = new StaminaCalculatorService();
        assertTrue(staminaCalculatorService.computeStamina(team1));
    }
}