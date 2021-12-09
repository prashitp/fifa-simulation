
package com;

import com.gameplay.Substitution.StaminaComputation.StaminaCalculatorService;
import com.gameplay.TeamSelection.ITeamSelectionController;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.models.ClubModel;
import com.models.PlayerModel;
import com.models.gameplay.TeamSelection.Lineup;
import com.models.gameplay.player_transfers.TransferService;
import com.models.gameplay.player_transfers.player_rearrangement.ClubPlayerPreferenceMatching;
import com.models.gameplay.player_transfers.player_rearrangement.IClubPlayerPreferenceMatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        System.out.println("Welcome to Fifa Simulation");
        ITeamSelectionController teamSelectionController = new TeamSelectionController("Chelsea","Manchester United");
        Lineup lineup = teamSelectionController.getTeam();

        System.out.println(lineup.getFormation().toString());

        for (PlayerModel player:lineup.getplaying11().keySet()) {
            System.out.println(player.getPlayerName() +" - "+ lineup.getplaying11().get(player));

        }
        StaminaCalculatorService staminaCalculatorService = new StaminaCalculatorService();
        staminaCalculatorService.computeStamina(lineup.getplaying11());

       // TransferService tf = TransferService.getInstance(Arrays.asList(Constants.PLAYERS));
    }
}
