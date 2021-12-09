package com.gameplay.controller;

import com.gameplay.service.ICardsService;
import com.gameplay.service.RedCardService;
import com.gameplay.service.YellowCardService;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.CardType;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class CardsController implements ICardsController{

    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;
    HashMap<CardType, List<PlayerModel>> fouls;

    protected CardsController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.team1 = team1;
        this.team2 = team2;
        fouls = fetchFouls();
        updateFouls();
    }

    private void updateFouls(){
        for(PlayerModel player: fouls.get(CardType.RED)){
            player.availability = false;
            player.yellowCard = false;
        }
        for(PlayerModel player: fouls.get(CardType.YELLOW)){
            if(player.yellowCard){
                player.availability = false;
                player.yellowCard = false;
            }else{
                player.availability = true;
                player.yellowCard = true;
            }
        }
    }

    @Override
    public HashMap<CardType, List<PlayerModel>> fetchFouls() {
        fouls = new HashMap<>();

        ICardsService redCard = new RedCardService(team1, team2);
        ICardsService yellowCard = new YellowCardService(team1, team2);

        fouls.put(CardType.RED, redCard.getPlayers());
        fouls.put(CardType.YELLOW, yellowCard.getPlayers());

        return fouls;
    }
}
