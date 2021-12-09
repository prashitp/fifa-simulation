
package com;

import com.gameplay.player_transfers.PlayerTransfersController;


import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        System.out.println("Welcome to Fifa Simulation");
//        ITeamSelectionController teamSelectionController = new TeamSelectionController("Chelsea","Manchester United");
//        Lineup lineup = teamSelectionController.getTeam();
//
//        System.out.println(lineup.getFormation().toString());
//
//        for (PlayerModel player:lineup.getplaying11().keySet()) {
//            System.out.println(player.getPlayerName() +" - "+ lineup.getplaying11().get(player));
//
//        }
//        StaminaCalculatorService staminaCalculatorService = new StaminaCalculatorService();
//        staminaCalculatorService.computeStamina(lineup);

       PlayerTransfersController tf = PlayerTransfersController.getInstance(Arrays.asList(Constants.PLAYERS));
    }
}
