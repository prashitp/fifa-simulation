package com.models.gameplay.player_transfers.player_rearrangement;

import com.Constants;
import com.models.ClubAttributes;
import com.models.ClubModel;
import com.models.gameplay.player_transfers.PlayerTransferWrapper;

import java.util.*;

public class ReArrangePlayersByClubRanks implements IReArrangePlayersByClubRanks {

    private static ReArrangePlayersByClubRanks reArrangePlayersByClubRanks;
    public static ReArrangePlayersByClubRanks getInstance() {
        if (reArrangePlayersByClubRanks == null) {
            reArrangePlayersByClubRanks = new ReArrangePlayersByClubRanks();
        }
        return reArrangePlayersByClubRanks;
    }

    @Override
    public HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> reArrangeClubToPlayerMappings(HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> transferPlayerMap) {
        List<ClubModel> clubs = computeClubPositionsByRanking();
        HashMap<String, HashMap<String, List<PlayerTransferWrapper>>> reArrangedPlayerMappingBasedOnClubPositions = new HashMap<>();
        for(ClubModel club: clubs) {
            reArrangedPlayerMappingBasedOnClubPositions.put(club.getClubName(), transferPlayerMap.get(club.getClubName()));
        }
        System.out.println(reArrangedPlayerMappingBasedOnClubPositions);
        return reArrangedPlayerMappingBasedOnClubPositions;
    }
    @Override
    public List<ClubModel> getHighestPayingClub() {
        List<ClubModel> clubs = Arrays.asList(Constants.CLUBS);
        clubs.sort((clubOne, clubTwo) -> (int) (clubTwo.transferBudget - clubOne.transferBudget));
        return clubs;
    }

    private List<ClubModel> computeClubPositionsByRanking() {
        List<ClubModel> clubs = Arrays.asList(Constants.CLUBS);
        clubs.sort((clubOne, clubTwo) -> (clubTwo.attributes.get(ClubAttributes.OVERALL) - clubOne.attributes.get(ClubAttributes.OVERALL)));
        return clubs;
    }




}
