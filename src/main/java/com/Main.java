
package com;

import com.gameplay.ScoreLine.ScoreLineController;
import com.gameplay.TeamSelection.ITeamSelectionController;
import com.gameplay.TeamSelection.TeamSelectionController;
import com.models.ClubModel;
import com.models.SetPieceType;
import com.models.gameplay.TeamSelection.Lineup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        System.out.println("Welcome to Fifa Simulation");

        ITeamSelectionController teamSelectionController = new TeamSelectionController("Chelsea","Liverpool");
        List<Lineup> lineups = teamSelectionController.getSquads();

//        for (PlayerModel player:lineup.getplaying11().keySet()) {
//            System.out.println(player.getPlayerName() +" - "+ lineup.getplaying11().get(player));
//        }

        HashMap<SetPieceType, List<Integer>> setPieces = new HashMap<>();
        List<Integer> freeKicks = new ArrayList<>();
        List<Integer> corners = new ArrayList<>();
        List<Integer> penalty = new ArrayList<>();

        freeKicks.add(15);
        freeKicks.add(10);

        corners.add(12);
        corners.add(7);

        penalty.add(0);
        penalty.add(1);

        setPieces.put(SetPieceType.FREE_KICK,freeKicks);
        setPieces.put(SetPieceType.CORNER_KICK,corners);
        setPieces.put(SetPieceType.PENALTY_KICK,penalty);

        ScoreLineController scoreLineController = new ScoreLineController("Chelsea", "Liverpool",
                lineups, setPieces);
        HashMap<ClubModel,Integer> scoreLine = scoreLineController.getScoreLine();

        for (ClubModel club:scoreLine.keySet()) {
            System.out.println(scoreLine.get(club));
        }
    }
}
