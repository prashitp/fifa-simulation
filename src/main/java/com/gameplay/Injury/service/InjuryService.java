package com.gameplay.Injury.service;

import com.Constants;
import com.gameplay.Injury.constants.InjuryConstants;
import com.models.*;

import java.util.*;

/**
 * @author vasugamdha
 */

public class InjuryService implements IInjuryService {

    protected final List<PlayerModel> players;
    protected final List<PlayerModel> candidates;
    protected HashMap<PlayerModel, Integer> injuredPlayers;
    protected double club1overall;
    protected double club2overall;
    protected final Random random;
    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;
    String homeClub;
    String awayClub;

    public InjuryService(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {

        this.team1 = team1;
        this.team2 = team2;
        players = new ArrayList<>();
        candidates = new ArrayList<>();
        random = new Random();
        club1overall = 0;
        club2overall = 0;

        setup();
        updateInjuries();
    }

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
                if(player.getValue().equals(PlayingPosition.GOALKEEPER) && random.nextDouble()>0.05){
                    continue;
                }
                players.add(player.getKey());
            }
        }

        assignInjuries();
    }

    @Override
    public HashMap<PlayerModel, Integer> getInjuredPlayers(){
        return injuredPlayers;
    }

    private void updateInjuries(){
        for(Map.Entry<PlayerModel, Integer> entry: injuredPlayers.entrySet()){
            PlayerModel player = entry.getKey();
            int days = entry.getValue();
            player.injuredForMatches = days;
            player.availability = false;
        }
    }

    private void calculateDays(){
        injuredPlayers = new HashMap<>();

        for(PlayerModel player: candidates){
            int stamina = player.skills.get(PlayerAttributes.POWER_STAMINA);
            int days = (int) Math.ceil((random.nextDouble()*5) * (1-(stamina/100.0)));
            injuredPlayers.put(player,days);
        }
    }

    private List<Map.Entry<PlayerModel, Double>> calculateInjuryFactors(List<PlayerModel> players){
        HashMap<PlayerModel, Double> map = new HashMap<>();
        List<Map.Entry<PlayerModel, Double>> mapList;
        double overall;
        for (PlayerModel player : players) {
            if(player.club.equals(homeClub)){
                overall = club1overall;
            }else{
                overall = club2overall;
            }
            double injuryFactor = 0;
            for (PlayerAttributes skill : InjuryConstants.INJURY_SKILLS) {
                double value = player.skills.get(skill);
                injuryFactor = injuryFactor + (value / (double) InjuryConstants.INJURY_SKILLS.length);
            }
            map.put(player, injuryFactor * overall / 100);
        }
        mapList = sortHashMap(map);
        return mapList;
    }

    private List<Map.Entry<PlayerModel, Double>> sortHashMap(HashMap<PlayerModel, Double> map) {
        List<Map.Entry<PlayerModel, Double>> mapList = new ArrayList<>(map.entrySet());
        mapList.sort(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.naturalOrder())));

        return mapList;
    }

    protected int getIndex(int size){
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
        return index%3;
    }

    private void assignInjuries(){
        List<Map.Entry<PlayerModel, Double>> injuryMap = calculateInjuryFactors(players);
        int breakIndex = getIndex(injuryMap.size());
        int itr = 0;
        for(Map.Entry<PlayerModel, Double> player : injuryMap){
            if(breakIndex>itr++) {
                candidates.add(player.getKey());
            }else{
                break;
            }
        }
        calculateDays();
    }
}
