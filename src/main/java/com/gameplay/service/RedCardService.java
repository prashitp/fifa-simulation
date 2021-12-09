package com.gameplay.service;

import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vasugamdha
 */

public class RedCardService extends CardsService implements IRedCardService {

    protected static final List<PlayerModel> candidates = new ArrayList<>();

    public RedCardService(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
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
        int breakIndex = getProbablePlayerCount(playerSkillDiff.size())%2;
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
