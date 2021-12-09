package com.models.gameplay.Substitution;

import com.utils.CommonFunctions;
import com.models.PlayerAttributes;
import com.models.PlayingPosition;

import java.util.HashMap;
/**
 * @author Mayank Sareen
 */

public class StaminaFactors {
    public static HashMap<PlayingPosition,HashMap<PlayerAttributes, Double>> getMultiplicativeFactors() {
        HashMap<PlayingPosition,HashMap<PlayerAttributes, Double>> multiplicativeFactors = new HashMap<PlayingPosition, HashMap<PlayerAttributes, Double>>() {{
            put(PlayingPosition.FORWARD, getForwardPositionFactors());
            put(PlayingPosition.MIDFIELDER, getMidFielderPositionFactors());
            put(PlayingPosition.DEFENDER, getDefenderPositionFactors());
            put(PlayingPosition.GOALKEEPER, getGoalKeeperPositionFactors());
        }};
        return multiplicativeFactors;
    }
    public static HashMap<PlayingPosition, Double> getPercentageReducedByPosition() {
        //System.out.println("a"+CommonFunctions.generateRandomDoubleBetweenRange(5, 6));
        HashMap<PlayingPosition, Double> getPercentage = new HashMap<PlayingPosition, Double>() {{
            put(PlayingPosition.FORWARD, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(7, 8)); //0.70
            put(PlayingPosition.MIDFIELDER, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(6, 7)); //0.65;
            put(PlayingPosition.DEFENDER, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(6, 8)); //0.80);
            put(PlayingPosition.GOALKEEPER, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(5, 6));// 0.60);
        }};
        return getPercentage;
    }
    private static HashMap<PlayerAttributes, Double> getForwardPositionFactors() {
        HashMap<PlayerAttributes, Double> forwardPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.POWER_STRENGTH, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.POWER_LONG_SHOTS, CommonFunctions.generateRandomDoubleBetweenRange(6, 10));
                put(PlayerAttributes.MOVEMENT_SPRINT_SPEED, CommonFunctions.generateRandomDoubleBetweenRange(8, 10));
                put(PlayerAttributes.MOVEMENT_ACCELERATION, CommonFunctions.generateRandomDoubleBetweenRange(5, 10));
                put(PlayerAttributes.MOVEMENT_AGILITY, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.MOVEMENT_BALANCE, CommonFunctions.generateRandomDoubleBetweenRange(3, 10));
                put(PlayerAttributes.DRIBBLING, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.PASSING, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.ATTACKING_CROSSING, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
                put(PlayerAttributes.ATTACKING_FINISHING, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.ATTACKING_SHORT_PASSING, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
            }};
        return forwardPositionAttributeFactors;
    }

    private static HashMap<PlayerAttributes, Double> getMidFielderPositionFactors() {
        HashMap<PlayerAttributes, Double> midFielderPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.POWER_STRENGTH, CommonFunctions.generateRandomDoubleBetweenRange(3, 8));
                put(PlayerAttributes.POWER_LONG_SHOTS, CommonFunctions.generateRandomDoubleBetweenRange(4, 8));
                put(PlayerAttributes.MOVEMENT_SPRINT_SPEED, CommonFunctions.generateRandomDoubleBetweenRange(6, 8));
                put(PlayerAttributes.MOVEMENT_ACCELERATION, CommonFunctions.generateRandomDoubleBetweenRange(5, 8));
                put(PlayerAttributes.MOVEMENT_AGILITY, CommonFunctions.generateRandomDoubleBetweenRange(5, 8));
                put(PlayerAttributes.MOVEMENT_BALANCE, CommonFunctions.generateRandomDoubleBetweenRange(3, 8));
                put(PlayerAttributes.DRIBBLING, CommonFunctions.generateRandomDoubleBetweenRange(3, 8));
                put(PlayerAttributes.PASSING, CommonFunctions.generateRandomDoubleBetweenRange(2, 8));
                put(PlayerAttributes.ATTACKING_CROSSING, CommonFunctions.generateRandomDoubleBetweenRange(1, 8));
            }};
        return midFielderPositionAttributeFactors;
    }

    private static HashMap<PlayerAttributes, Double> getDefenderPositionFactors() {
        HashMap<PlayerAttributes, Double> defenderPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.POWER_STRENGTH, CommonFunctions.generateRandomDoubleBetweenRange(3, 5));
                put(PlayerAttributes.POWER_LONG_SHOTS, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.MOVEMENT_SPRINT_SPEED, CommonFunctions.generateRandomDoubleBetweenRange(4, 7));
                put(PlayerAttributes.MOVEMENT_ACCELERATION, CommonFunctions.generateRandomDoubleBetweenRange(6, 8));
                put(PlayerAttributes.MOVEMENT_AGILITY, CommonFunctions.generateRandomDoubleBetweenRange(3, 5));
                put(PlayerAttributes.MOVEMENT_BALANCE, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.DEFENDING, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
                put(PlayerAttributes.DEFENDING_SLIDING_TACKLE, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.DEFENDING_STANDING_TACKLE, CommonFunctions.generateRandomDoubleBetweenRange(3, 5));
            }};
        return defenderPositionAttributeFactors;
    }

    private static HashMap<PlayerAttributes, Double> getGoalKeeperPositionFactors() {
        HashMap<PlayerAttributes, Double> goalkeeperPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.POWER_STRENGTH, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.POWER_LONG_SHOTS, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.MOVEMENT_SPRINT_SPEED, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.MOVEMENT_ACCELERATION, CommonFunctions.generateRandomDoubleBetweenRange(3, 6));
                put(PlayerAttributes.MOVEMENT_AGILITY, CommonFunctions.generateRandomDoubleBetweenRange(1, 4));
                put(PlayerAttributes.MOVEMENT_BALANCE, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.GOALKEEPING_DIVING, CommonFunctions.generateRandomDoubleBetweenRange(2, 5));
                put(PlayerAttributes.GOALKEEPING_SPEED, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.GOALKEEPING_KICKING, CommonFunctions.generateRandomDoubleBetweenRange(2, 3));
                put(PlayerAttributes.GOALKEEPING_HANDLING, CommonFunctions.generateRandomDoubleBetweenRange(1, 4));
                put(PlayerAttributes.GOALKEEPING_REFLEXES, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
            }};
        return goalkeeperPositionAttributeFactors;
    }
}
