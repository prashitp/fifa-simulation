package com.gameplay.Fouls;

import com.Constants;
import com.models.*;

import java.util.*;

/**
 * @author vasugamdha
 */

public abstract class Cards implements ICards {

    protected final List<PlayerModel> players;
    protected double club1overall;
    protected double club2overall;
    protected final Random random;
    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;
    String homeClub;
    String awayClub;

    protected Cards(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.team1 = team1;
        this.team2 = team2;
        players = new ArrayList<>();
        club1overall = 0;
        club2overall = 0;
        random = new Random();
        setup();
    }

    protected abstract void assignCards();

    public abstract List<PlayerModel> getPlayers();

    @Override
    public void setup(){
        // Fetch Club names
        Set<Map.Entry<PlayerModel, PlayingPosition>> homeTeam = team1.entrySet();
        Map.Entry<PlayerModel, PlayingPosition> homePlayer = homeTeam.stream().findFirst().get();
        homeClub = homePlayer.getKey().club;

        Set<Map.Entry<PlayerModel, PlayingPosition>> awayTeam = team1.entrySet();
        Map.Entry<PlayerModel, PlayingPosition> awayPlayer = awayTeam.stream().findFirst().get();
        awayClub = awayPlayer.getKey().club;

        // Fetch respective "OVERALL" Club attribute value
        for (ClubModel club : Constants.CLUBS) {
            if (club.getClubName().equals(homeClub)) {
                club1overall = club.attributes.get(ClubAttributes.OVERALL);
            }
            if (club.getClubName().equals(awayClub)) {
                club2overall = club.attributes.get(ClubAttributes.OVERALL);
            }
            if (club1overall != 0 && club2overall != 0) {
                break;
            }
        }

        for (HashMap<PlayerModel, PlayingPosition> team : List.of(team1, team2)) {
            for (Map.Entry<PlayerModel, PlayingPosition> player : team.entrySet()) {
                if(player.getValue().equals(PlayingPosition.GOALKEEPER) && random.nextDouble()>0.1){
                    continue;
                }
                if(player.getValue().equals(PlayingPosition.FORWARD) && random.nextDouble()>0.3){
                    continue;
                }
                players.add(player.getKey());
            }
        }

    }

    protected List<Map.Entry<PlayerModel, Double>> getAverage(List<PlayerModel> players) {
        HashMap<PlayerModel, Double> map = new HashMap<>();
        List<Map.Entry<PlayerModel, Double>> mapList;
        double overall;
        for (PlayerModel player : players) {
            if(player.club.equals(homeClub)){
                overall = club1overall;
            }else{
                overall = club2overall;
            }
            double positiveAverage = 0, negativeAverage = 0;
            for (PlayerAttributes skill : FoulsConstants.POSITIVE_SKILLS) {
                double value = player.skills.get(skill);
                positiveAverage = positiveAverage + (value / (double) FoulsConstants.POSITIVE_SKILLS.length);
            }
            for (PlayerAttributes skill : FoulsConstants.NEGATIVE_SKILLS) {
                double value = player.skills.get(skill);
                negativeAverage = negativeAverage + (value / (double) FoulsConstants.NEGATIVE_SKILLS.length);
            }
            map.put(player, (positiveAverage - negativeAverage) * overall / 100);
        }
        mapList = sortHashMap(map);
        return mapList;
    }

    private List<Map.Entry<PlayerModel, Double>> sortHashMap(HashMap<PlayerModel, Double> map) {
        List<Map.Entry<PlayerModel, Double>> mapList = new ArrayList<>(map.entrySet());
        mapList.sort(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.naturalOrder())));

        return mapList;
    }

    protected int getProbablePlayerCount(int size){
        double[] array = new double[size];
        int index=size-1;
        double probConstant = 1.35;
        double rand = random.nextDouble();
        for(int i=0; i<size; i++){
            array[i] = (double) (i+1)/ (size*probConstant);
            if(array[i]>rand){
                index = i;
                break;
            }
        }
        return index;
    }

}
