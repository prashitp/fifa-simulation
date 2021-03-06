package com.gameplay.service;

import com.utils.Constants;
import com.models.PlayerModel;
import com.models.PlayingPosition;

import java.util.HashMap;
import java.util.List;

/**
 * @author vasugamdha
 */


public class CornerKickService extends KickService implements ICornerKickService {

    public CornerKickService(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
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
        t1forward = calculateProbableSkillValue(forward.get(0), Constants.ATTACKING_SKILLS);
        t1midfielder = calculateProbableSkillValue(midfielder.get(0), Constants.MIDFIELD_SKILLS);
        t2defender = calculateProbableSkillValue(defender.get(1), Constants.DEFENDING_SKILLS);
        t2goalkeeper = calculateProbableSkillValue(goalkeeper.get(1), Constants.GOALKEEPING_SKILLS);

        t1threshold = (t1forward + t1midfielder) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        t2threshold = (t2defender + t2goalkeeper) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        calculateCornerKicks(t1threshold, t2threshold);

        // Combination 2
        t1defender = calculateProbableSkillValue(defender.get(0), Constants.DEFENDING_SKILLS);
        t1goalkeeper = calculateProbableSkillValue(goalkeeper.get(0), Constants.GOALKEEPING_SKILLS);
        t2forward = calculateProbableSkillValue(forward.get(1), Constants.ATTACKING_SKILLS);
        t2midfielder = calculateProbableSkillValue(midfielder.get(1), Constants.MIDFIELD_SKILLS);

        t1threshold = (t2forward + t2midfielder) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        t2threshold = (t1defender + t1goalkeeper) / (t1forward + t1midfielder + t2defender + t2goalkeeper);
        calculateCornerKicks(t1threshold, t2threshold);
    }

    @Override
    public void calculateCornerKicks(double threshold1, double threshold2) {

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
