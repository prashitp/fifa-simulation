package com.gameplay.service;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.*;

/**
 * @author vasugamdha
 */

public class YellowCardService extends CardsService implements IYellowCardService {

    protected static final List<PlayerModel> candidates = new ArrayList<>();

    public YellowCardService(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        super(team1, team2);
        assignCards();
    }

    @Override
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
