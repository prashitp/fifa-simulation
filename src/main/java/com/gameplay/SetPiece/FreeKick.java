package com.gameplay.SetPiece;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class FreeKick extends SetPiece {

    public FreeKick(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        super(team1, team2);
    }

    @Override
    public List<Integer> getSetPiece() {
        return List.of(kick1, kick2);
    }

    @Override
    protected void deduceSkills(List<List<PlayerModel>> forward, List<List<PlayerModel>> midfielder,
                                List<List<PlayerModel>> defender, List<List<PlayerModel>> goalkeeper) {
        // averages of skill types of individuals
        double t1forward, t1midfielder, t2defender, t2goalkeeper, t1sum;
        double t2forward, t2midfielder, t1defender, t1goalkeeper, t2sum;

        // Team 1
        t1forward = calculateProbableSkillValue(forward.get(0), Constants.attackingSkills);
        t1midfielder = calculateProbableSkillValue(midfielder.get(0), Constants.midfieldSkills);
        t1defender = calculateProbableSkillValue(defender.get(0), Constants.defendingSkills);
        t1goalkeeper = calculateProbableSkillValue(goalkeeper.get(0), Constants.goalkeepingSkills);
        t1sum = (t1forward + t1midfielder + t1defender + t1goalkeeper) / 4;

        // Team 2
        t2defender = calculateProbableSkillValue(defender.get(1), Constants.defendingSkills);
        t2goalkeeper = calculateProbableSkillValue(goalkeeper.get(1), Constants.goalkeepingSkills);
        t2forward = calculateProbableSkillValue(forward.get(1), Constants.attackingSkills);
        t2midfielder = calculateProbableSkillValue(midfielder.get(1), Constants.midfieldSkills);
        t2sum = (t2forward + t2midfielder + t2defender + t2goalkeeper) / 4;

        calculateKicks(t1sum, t2sum);
    }

    protected void calculateKicks(double threshold1, double threshold2) {

        double t1threshold, t2threshold;
        t1threshold = (threshold1) / (threshold1 + threshold2);
        t2threshold = (threshold2) / (threshold1 + threshold2);
        double upperLimit = 40;
        double lowerLimit = 25;
        double kicks = (random.nextDouble() * (upperLimit - lowerLimit)) + lowerLimit;

        kick1 = (int) Math.ceil(kicks * (t1threshold) * (club1overall / 100));
        kick2 = (int) Math.ceil(kicks * (t2threshold) * (club2overall / 100));

        // reverse possibility
        if (random.nextDouble() > 0.80) {
            kick1 = kick1 + kick2;
            kick2 = kick1 - kick2;
            kick1 = kick1 - kick2;
        }
    }
}
