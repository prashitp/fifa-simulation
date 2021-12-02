package com.gameplay.SetPiece;

import com.Constants;
import com.models.*;

import java.util.*;

/**
 * @author vasugamdha
 */

public class CornerKick implements ISetPiece{
    private HashMap<PlayerModel, PlayingPosition> team1;
    private HashMap<PlayerModel, PlayingPosition> team2;
    private static double club1overall=0;
    private static double club2overall=0;

    private static final List<List<PlayerModel>> forward= new ArrayList<>();
    private static final List<List<PlayerModel>> midfielder= new ArrayList<>();
    private static final List<List<PlayerModel>> defender= new ArrayList<>();
    private static final List<List<PlayerModel>> goalkeeper= new ArrayList<>();
    private final Random random = new Random();
    private int kick1 = 0;
    private int kick2 = 0;


    public CornerKick(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2){
        this.team1 = team1;
        this.team2 = team2;

        setup();
    }



    @Override
    public void setup(){
        // Fetch Club names
        String club1 = team1.entrySet().stream().findFirst().get().getKey().club;
        String club2 = team2.entrySet().stream().findFirst().get().getKey().club;

        // Fetch respective overall Club attribute value
        for(ClubModel club: Constants.CLUBS){
            if(club.getClubName().equals(club1)){
                club1overall = club.attributes.get(ClubAttributes.Overall);
            }
            if(club.getClubName().equals(club2)){
                club2overall = club.attributes.get(ClubAttributes.Overall);
            }
            if(club1overall!=0 && club2overall != 0){
                break;
            }
        }

        // Divide team 1 based on playing positions
        for (Map.Entry<PlayerModel, PlayingPosition> player: team1.entrySet()){
            if (player.getValue() == PlayingPosition.FORWARD){
                forward.add(List.of(player.getKey()));
            }
            else if (player.getValue() == PlayingPosition.MIDFIEDLER){
                midfielder.add(List.of(player.getKey()));
            }
            else if (player.getValue() == PlayingPosition.DEFENDER){
                defender.add(List.of(player.getKey()));
            }
            else if (player.getValue() == PlayingPosition.GOALKEEPER){
                goalkeeper.add(List.of(player.getKey()));
            }
        }

        // Divide team 2 based on playing positions
        for (Map.Entry<PlayerModel, PlayingPosition> player: team2.entrySet()){
            if (player.getValue() == PlayingPosition.FORWARD){
                forward.add(List.of(player.getKey()));
            }
            else if (player.getValue() == PlayingPosition.MIDFIEDLER){
                midfielder.add(List.of(player.getKey()));
            }
            else if (player.getValue() == PlayingPosition.DEFENDER){
                defender.add(List.of(player.getKey()));
            }
            else if (player.getValue() == PlayingPosition.GOALKEEPER){
                goalkeeper.add(List.of(player.getKey()));
            }
        }

        // Formulating all skills to single value based on their skill
        deduceSkills(forward,midfielder,defender,goalkeeper);
    }

    @Override
    public List<Integer> getSetPiece() {
        return List.of(kick1,kick2);
    }

    private void deduceSkills(List<List<PlayerModel>> forward, List<List<PlayerModel>> midfielder,
                             List<List<PlayerModel>> defender, List<List<PlayerModel>> goalkeeper)
    {
        // averages of skill types of individuals
        double t1forward, t1midfielder, t2defender, t2goalkeeper;
        double t2forward, t2midfielder, t1defender, t1goalkeeper;
        double t1threshold, t2threshold;

        // Combination 1
        t1forward = calculateProbableSkillValue(forward.get(0), Constants.attackingSkills);
        t1midfielder = calculateProbableSkillValue(midfielder.get(0), Constants.midfieldSkills);
        t2defender = calculateProbableSkillValue(defender.get(1), Constants.defendingSkills);
        t2goalkeeper = calculateProbableSkillValue(goalkeeper.get(1), Constants.goalkeepingSkills);

        t1threshold = (t1forward+t1midfielder) / (t1forward+t1midfielder+t2defender+t2goalkeeper);
        t2threshold = (t2defender+t2goalkeeper) / (t1forward+t1midfielder+t2defender+t2goalkeeper);
        calculateKicks(t1threshold,t2threshold);

        // Combination 2
        t1defender = calculateProbableSkillValue(defender.get(0), Constants.defendingSkills);
        t1goalkeeper = calculateProbableSkillValue(goalkeeper.get(0), Constants.goalkeepingSkills);
        t2forward = calculateProbableSkillValue(forward.get(1), Constants.attackingSkills);
        t2midfielder = calculateProbableSkillValue(midfielder.get(1), Constants.midfieldSkills);

        t1threshold = (t1defender+t1goalkeeper) / (t2forward+t2midfielder+t1defender+t1goalkeeper);
        t2threshold = (t2forward+t2midfielder) / (t2forward+t2midfielder+t1defender+t1goalkeeper);
        calculateKicks(t1threshold,t2threshold);
    }

    private double calculateProbableSkillValue(List<PlayerModel> players, PlayerAttributes[] skills)
    {
        double minavg=0, favg=0, maxavg=0;
        HashMap<String, Double> math = new HashMap<>();
        HashMap<PlayerModel, HashMap<String, Double>> map = new HashMap<>();
        double total_players = players.size();
        for (PlayerModel player : players) {
            double min=100, avg=0, max=0;
            for (PlayerAttributes skill : skills) {
                double value = player.skills.get(skill);
                max = Math.max(value, max);
                min = Math.min(value, min);
                avg = avg + ( value / (double) skills.length);
            }
            minavg += min / total_players;
            maxavg += max / total_players;
            favg += avg / total_players;
        }
        double probable_skill_value = (minavg*0.15) + (maxavg*0.15) + (favg*0.7);
        return probable_skill_value;
    }

    private void calculateKicks(double threshold1, double threshold2){

        double rand = (random.nextDouble() * Math.abs(threshold1 - threshold2)) + Math.min(threshold1,threshold2);
        double fold = Math.max(Math.abs(threshold1-rand), Math.abs(threshold2-rand));
        double mul = Math.max(100-threshold1, 100-threshold2);
        int kicks = (int) Math.ceil(mul*fold/2);

        kick1 += Math.ceil(((double) kicks / 100) * (threshold1) * (club1overall));
        kick2 += Math.ceil(((double) kicks / 100) * (threshold2) * (club2overall));

        if(random.nextDouble()>0.90){
            kick1 = kick1 + kick2;
            kick2 = kick1 - kick2;
            kick1 = kick1 - kick2;
        }
    }


    public static void main(String[] args) {
        HashMap<PlayerModel,PlayingPosition> team1 = new HashMap<>();
        HashMap<PlayerModel,PlayingPosition> team2 = new HashMap<>();
        team1.put(Constants.PLAYERS[0], PlayingPosition.FORWARD);
        team1.put(Constants.PLAYERS[1], PlayingPosition.MIDFIEDLER);
        team1.put(Constants.PLAYERS[2], PlayingPosition.DEFENDER);
        team1.put(Constants.PLAYERS[3], PlayingPosition.GOALKEEPER);

        team2.put(Constants.PLAYERS[4], PlayingPosition.FORWARD);
        team2.put(Constants.PLAYERS[5], PlayingPosition.MIDFIEDLER);
        team2.put(Constants.PLAYERS[6], PlayingPosition.DEFENDER);
        team2.put(Constants.PLAYERS[7], PlayingPosition.GOALKEEPER);

        ISetPiece cornerKick = new CornerKick(team1, team2);
        System.out.println(cornerKick.getSetPiece());
    }
}
