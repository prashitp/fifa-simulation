package com.gameplay.SetPiece;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class CornerKick extends SetPiece {

    public CornerKick(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
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
        double t1forward, t1midfielder, t2defender, t2goalkeeper;
        double t2forward, t2midfielder, t1defender, t1goalkeeper;
        double t1threshold, t2threshold;

        // Combination 1
        t1forward = calculateProbableSkillValue(forward.get(0), Constants.attackingSkills);
        t1midfielder = calculateProbableSkillValue(midfielder.get(0), Constants.midfieldSkills);
        t2defender = calculateProbableSkillValue(defender.get(1), Constants.defendingSkills);
        t2goalkeeper = calculateProbableSkillValue(goalkeeper.get(1), Constants.goalkeepingSkills);

        t1threshold = (t1forward + t1midfielder) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        t2threshold = (t2defender + t2goalkeeper) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        calculateKicks(t1threshold, t2threshold);

        // Combination 2
        t1defender = calculateProbableSkillValue(defender.get(0), Constants.defendingSkills);
        t1goalkeeper = calculateProbableSkillValue(goalkeeper.get(0), Constants.goalkeepingSkills);
        t2forward = calculateProbableSkillValue(forward.get(1), Constants.attackingSkills);
        t2midfielder = calculateProbableSkillValue(midfielder.get(1), Constants.midfieldSkills);

        t1threshold = (t2forward + t2midfielder) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        t2threshold = (t1defender + t1goalkeeper) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        calculateKicks(t1threshold, t2threshold);
    }

    protected void calculateKicks(double threshold1, double threshold2) {

        double rand = (random.nextDouble() * Math.abs(threshold1 - threshold2)) + Math.min(threshold1, threshold2);
        double fold = Math.max(Math.abs(threshold1 - rand), Math.abs(threshold2 - rand));
        double mul = Math.max(100 - threshold1, 100 - threshold2);
        double kicks = Math.ceil(mul * fold / 2);

        kick1 += Math.ceil((kicks / 100) * (threshold1) * (club1overall));
        kick2 += Math.ceil((kicks / 100) * (threshold2) * (club2overall));

        // reverse possibility
        if (random.nextDouble() > 0.90) {
            kick1 = kick1 + kick2;
            kick2 = kick1 - kick2;
            kick1 = kick1 - kick2;
        }
    }
}
