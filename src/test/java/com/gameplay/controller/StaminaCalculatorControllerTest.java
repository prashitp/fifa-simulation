package com.gameplay.controller;

import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.utils.Constants;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author mayanksareen
 */
public class StaminaCalculatorControllerTest {
    HashMap<PlayerModel, PlayingPosition> team1 = new HashMap<>();
    HashMap<PlayerModel,PlayingPosition> team2 = new HashMap<>();

    public StaminaCalculatorControllerTest(){
        team1.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
        team1.put(Constants.PLAYERS[12], PlayingPosition.MIDFIELDER);
        team1.put(Constants.PLAYERS[20], PlayingPosition.DEFENDER);
        team1.put(Constants.PLAYERS[39], PlayingPosition.GOALKEEPER);

        team2.put(Constants.PLAYERS[1], PlayingPosition.FORWARD);
        team2.put(Constants.PLAYERS[8], PlayingPosition.MIDFIELDER);
        team2.put(Constants.PLAYERS[13], PlayingPosition.DEFENDER);
        team2.put(Constants.PLAYERS[18], PlayingPosition.GOALKEEPER);
    }

    @Test
    void getComputedStaminaTest() {
        IStaminaCalculatorController staminaCalculatorController = new StaminaCalculatorController();
        assertEquals("HashSet",staminaCalculatorController.computeStamina(team1, team2).getClass().getSimpleName());
    }
}
