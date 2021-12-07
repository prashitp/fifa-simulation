package com.gameplay.Fouls;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.*;

/**
 * @author vasugamdha
 */

public class YellowCard extends Cards{

    protected static final List<PlayerModel> candidates = new ArrayList<>();

    public YellowCard(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        super(team1, team2);
        assignCards();
    }

    public List<PlayerModel> getPlayers(){
        return candidates;
    }

    @Override
    public void assignCards(){
        List<Map.Entry<PlayerModel, Double>> playerSkillDiff = getAverage(players);
        int breakIndex = getProbablePlayerCount(playerSkillDiff.size())%5;
        int itr = 0;
        for(Map.Entry<PlayerModel, Double> player : playerSkillDiff){
            if(breakIndex>itr++) {
                candidates.add(player.getKey());
            }else{
                break;
            }
        }
    }
}
