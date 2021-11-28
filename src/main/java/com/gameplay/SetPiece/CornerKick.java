package com.gameplay.SetPiece;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.TeamSelection.FormationModel;
import com.models.gameplay.TeamSelection.FormationType;
import com.models.gameplay.TeamSelection.Lineup;

import java.util.*;

public class CornerKick implements ISetPiece{
    private static HashMap<PlayerModel, PlayingPosition> team1;
    private static HashMap<PlayerModel, PlayingPosition> team2;
    private static Random random = new Random();
    private static int kicks = 0;

    public CornerKick(Lineup team1, Lineup team2){
        this.team1 = team1.getplaying11();
        this.team2 = team2.getplaying11();
        calculateProbability();
    }

    public static int getCornerKicks(){
        return kicks;
    }

    @Override
    public void calculateProbability(){
        List<PlayerModel> forwardT1= new ArrayList<>();
        List<PlayerModel> forwardT2= new ArrayList<>();
        List<PlayerModel> midfielderT1= new ArrayList<>();
        List<PlayerModel> midfielderT2= new ArrayList<>();
        List<PlayerModel> defenderT1= new ArrayList<>();
        List<PlayerModel> defenderT2= new ArrayList<>();
        List<PlayerModel> goalkeeperT1= new ArrayList<>();
        List<PlayerModel> goalkeeperT2= new ArrayList<>();

        for (Map.Entry<PlayerModel, PlayingPosition> player: team1.entrySet()){
            if (player.getValue() == PlayingPosition.FORWARD){
                forwardT1.add(player.getKey());
            }
            else if (player.getValue() == PlayingPosition.MIDFIEDLER){
                midfielderT1.add(player.getKey());
            }
            else if (player.getValue() == PlayingPosition.DEFENDER){
                defenderT1.add(player.getKey());
            }
            else if (player.getValue() == PlayingPosition.GOALKEEPER){
                goalkeeperT1.add(player.getKey());
            }
        }

        for (Map.Entry<PlayerModel, PlayingPosition> player: team2.entrySet()){
            if (player.getValue() == PlayingPosition.FORWARD){
                forwardT2.add(player.getKey());
            }
            else if (player.getValue() == PlayingPosition.MIDFIEDLER){
                midfielderT2.add(player.getKey());
            }
            else if (player.getValue() == PlayingPosition.DEFENDER){
                defenderT2.add(player.getKey());
            }
            else if (player.getValue() == PlayingPosition.GOALKEEPER){
                goalkeeperT2.add(player.getKey());
            }
        }



    }

    public static void main(String[] args) {
        FormationModel formationModel = new FormationModel(4,5,1,FormationType.Defensive);
        HashMap<PlayerModel,PlayingPosition> playing11 = new HashMap<>();
        playing11.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
        Lineup lineup1 = new Lineup(formationModel,playing11);
        Lineup lineup2 = new Lineup(new FormationModel(3,4,3,FormationType.Neutral),
                new HashMap<>(){{put(Constants.PLAYERS[3], PlayingPosition.GOALKEEPER);}});
        ISetPiece ck = new CornerKick(lineup1,lineup2);
    }
}
