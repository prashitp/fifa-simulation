package com.gameplay.SetPiece;

import com.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */

public class PenaltyKick extends SetPiece {
    public PenaltyKick(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
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
        double t1forward, t2defender;
        double t2forward, t1defender;
        double t1threshold, t2threshold;

        // Combination 1
        t1forward = calculateProbableSkillValue(forward.get(0), Constants.attackingSkills);
        t2defender = calculateProbableSkillValue(defender.get(1), Constants.defendingSkills);

        t1threshold = (t1forward) / (t1forward + t2defender);
        t2threshold = (t2defender) / (t1forward + t2defender);
        calculateKicks(t1threshold, t2threshold);

        // Combination 2
        t2forward = calculateProbableSkillValue(forward.get(1), Constants.attackingSkills);
        t1defender = calculateProbableSkillValue(defender.get(0), Constants.defendingSkills);

        t1threshold = (t2forward) / (t2forward + t1defender);
        t2threshold = (t1defender) / (t2forward + t1defender);
        calculateKicks(t1threshold, t2threshold);
    }

    protected void calculateKicks(double threshold1, double threshold2) {

        double[] limit = {0, 1, 2};
        int kicks = (int) Math.round(random.nextDouble() * (limit[2] - limit[0]));
        kick1 += (int) Math.round(limit[kicks] * (threshold1));

        kicks = (int) Math.round(random.nextDouble() * (limit[2] - limit[0]));
        kick2 += (int) Math.round(kicks * (threshold2));

        if (random.nextDouble() < 0.3 && kick1 > 1) {
            kick1--;
        }
        if (random.nextDouble() < 0.3 && kick2 > 1) {
            kick2--;
        }

        // reverse possibility
        if (random.nextDouble() < 0.2) {
            kick1 = kick1 + kick2;
            kick2 = kick1 - kick2;
            kick1 = kick1 - kick2;
        }
    }
}
