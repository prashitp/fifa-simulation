package com.gameplay.Fouls;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;
import com.models.gameplay.CardType;

import java.util.HashMap;
import java.util.List;

public class CardsController implements ICardsController{

    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;

    protected CardsController(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }
    @Override
    public HashMap<CardType, List<PlayerModel>> getFouls() {
        HashMap<CardType, List<PlayerModel>> fouls = new HashMap<>();

        ICards redCard = new RedCard(team1, team2);
        ICards yellowCard = new YellowCard(team1, team2);

        fouls.put(CardType.RED, redCard.getPlayers());
        fouls.put(CardType.YELLOW, yellowCard.getPlayers());

        return fouls;
    }

    public static void main(String[] args) {
        HashMap<PlayerModel, PlayingPosition> team1 = new HashMap<>();
        HashMap<PlayerModel, PlayingPosition> team2 = new HashMap<>();
        team1.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
        team1.put(Constants.PLAYERS[12], PlayingPosition.MIDFIELDER);
        team1.put(Constants.PLAYERS[20], PlayingPosition.DEFENDER);
        team1.put(Constants.PLAYERS[39], PlayingPosition.GOALKEEPER);

        team2.put(Constants.PLAYERS[1], PlayingPosition.FORWARD);
        team2.put(Constants.PLAYERS[8], PlayingPosition.MIDFIELDER);
        team2.put(Constants.PLAYERS[13], PlayingPosition.DEFENDER);
        team2.put(Constants.PLAYERS[18], PlayingPosition.GOALKEEPER);

        ICardsController cc = new CardsController(team1, team2);
        System.out.println(cc.getFouls());
    }
}
