//Author - Prashit Patel
package com;

import com.gameplay.Substitution.StaminaComputation.IStaminaCalculatorService;
import com.gameplay.Substitution.StaminaComputation.StaminaCalculatorService;
import com.gameplay.TeamSelection.ITeamSelectionController;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.TeamSelection.Lineup;

import java.util.HashMap;

public class Main {
    public static void main(String args[]) {
        System.out.println("Welcome to Fifa Simulation");
        ITeamSelectionController teamSelectionController = new TeamSelectionController("Chelsea","Manchester United");
        Lineup lineup = teamSelectionController.getTeam();
        System.out.println(lineup.getFormation().toString());
        for (PlayerModel player: lineup.getplaying11().keySet()) {
            System.out.println(player.getPlayerName() +" - "+ lineup.getplaying11().get(player));
        }
        IStaminaCalculatorService staminaCalculatorService = new StaminaCalculatorService();
        staminaCalculatorService.computeStamina(lineup.getplaying11());

    }
}
