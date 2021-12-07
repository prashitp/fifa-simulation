package com.gameplay.SetPiece;

import com.Constants;
import com.models.*;

import java.util.*;

/**
 * @author vasugamdha
 */

public abstract class SetPiece implements ISetPiece {

    private static final List<List<PlayerModel>> forward = new ArrayList<>();
    private static final List<List<PlayerModel>> midfielder = new ArrayList<>();
    private static final List<List<PlayerModel>> defender = new ArrayList<>();
    private static final List<List<PlayerModel>> goalkeeper = new ArrayList<>();
    protected int kick1 = 0;
    protected int kick2 = 0;
    protected double club1overall = 0;
    protected double club2overall = 0;
    protected Random random = new Random();
    private final HashMap<PlayerModel, PlayingPosition> team1;
    private final HashMap<PlayerModel, PlayingPosition> team2;

    protected SetPiece(HashMap<PlayerModel, PlayingPosition> team1, HashMap<PlayerModel, PlayingPosition> team2) {
        this.team1 = team1;
        this.team2 = team2;
        setup();
    }

    @Override
    public abstract List<Integer> getSetPiece();

    protected abstract void deduceSkills(List<List<PlayerModel>> forward, List<List<PlayerModel>> midfielder,
                                         List<List<PlayerModel>> defender, List<List<PlayerModel>> goalkeeper);

    @Override
    public void setup() {
        // Fetch Club names
        Set<Map.Entry<PlayerModel, PlayingPosition>> homeTeam = team1.entrySet();
        Map.Entry<PlayerModel, PlayingPosition> homePlayer = homeTeam.stream().findFirst().get();
        String homeClub = homePlayer.getKey().club;

        Set<Map.Entry<PlayerModel, PlayingPosition>> awayTeam = team1.entrySet();
        Map.Entry<PlayerModel, PlayingPosition> awayPlayer = awayTeam.stream().findFirst().get();
        String awayClub = awayPlayer.getKey().club;

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

        // Divide team 1 and team 2 based on playing positions
        for (HashMap<PlayerModel, PlayingPosition> team : List.of(team1, team2)) {
            for (Map.Entry<PlayerModel, PlayingPosition> player : team.entrySet()) {
                if (player.getValue() == PlayingPosition.FORWARD) {
                    forward.add(List.of(player.getKey()));
                } else if (player.getValue() == PlayingPosition.MIDFIELDER) {
                    midfielder.add(List.of(player.getKey()));
                } else if (player.getValue() == PlayingPosition.DEFENDER) {
                    defender.add(List.of(player.getKey()));
                } else if (player.getValue() == PlayingPosition.GOALKEEPER) {
                    goalkeeper.add(List.of(player.getKey()));
                }
            }
        }

        // Formulating all skills to single value based on their skill
        deduceSkills(forward, midfielder, defender, goalkeeper);
    }

    protected double calculateProbableSkillValue(List<PlayerModel> players, PlayerAttributes[] skills) {
        double minavg = 0, favg = 0, maxavg = 0;
        double total_players = players.size();
        for (PlayerModel player : players) {
            double min = 100, avg = 0, max = 0;
            for (PlayerAttributes skill : skills) {
                double value = player.skills.get(skill);
                max = Math.max(value, max);
                min = Math.min(value, min);
                avg = avg + (value / (double) skills.length);
            }
            minavg += min / total_players;
            maxavg += max / total_players;
            favg += avg / total_players;
        }
        double probable_skill_value = (minavg * 0.15) + (maxavg * 0.15) + (favg * 0.7);
        return probable_skill_value;
    }
}
