package com.models.gameplay.Substitution;

import com.CommonFunctions;
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
            put(PlayingPosition.MIDFIEDLER, getMidFielderPositionFactors());
            put(PlayingPosition.DEFENDER, getDefenderPositionFactors());
            put(PlayingPosition.GOALKEEPER, getGoalKeeperPositionFactors());
        }};
        return multiplicativeFactors;
    }
    public static HashMap<PlayingPosition, Double> getPercentageReducedByPosition() {
        //System.out.println("a"+CommonFunctions.generateRandomDoubleBetweenRange(5, 6));
        HashMap<PlayingPosition, Double> getPercentage = new HashMap<PlayingPosition, Double>() {{
            put(PlayingPosition.FORWARD, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(7, 8)); //0.70
            put(PlayingPosition.MIDFIEDLER, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(6, 7)); //0.65;
            put(PlayingPosition.DEFENDER, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(6, 8)); //0.80);
            put(PlayingPosition.GOALKEEPER, 0.1 * CommonFunctions.generateRandomDoubleBetweenRange(5, 6));// 0.60);
        }};
        return getPercentage;
    }
    private static HashMap<PlayerAttributes, Double> getForwardPositionFactors() {
        HashMap<PlayerAttributes, Double> forwardPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.PowerStrength, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.PowerLongShots, CommonFunctions.generateRandomDoubleBetweenRange(6, 10));
                put(PlayerAttributes.MovementSprintSpeed, CommonFunctions.generateRandomDoubleBetweenRange(8, 10));
                put(PlayerAttributes.MovementAcceleration, CommonFunctions.generateRandomDoubleBetweenRange(5, 10));
                put(PlayerAttributes.MovementAgility, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.MovementBalance, CommonFunctions.generateRandomDoubleBetweenRange(3, 10));
                put(PlayerAttributes.Dribbling, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.Passing, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.AttackingCrossing, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
                put(PlayerAttributes.AttackingFinishing, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.AttackingShortPassing, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
            }};
        return forwardPositionAttributeFactors;
    }

    private static HashMap<PlayerAttributes, Double> getMidFielderPositionFactors() {
        HashMap<PlayerAttributes, Double> midFielderPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.PowerStrength, CommonFunctions.generateRandomDoubleBetweenRange(3, 8));
                put(PlayerAttributes.PowerLongShots, CommonFunctions.generateRandomDoubleBetweenRange(4, 8));
                put(PlayerAttributes.MovementSprintSpeed, CommonFunctions.generateRandomDoubleBetweenRange(6, 8));
                put(PlayerAttributes.MovementAcceleration, CommonFunctions.generateRandomDoubleBetweenRange(5, 8));
                put(PlayerAttributes.MovementAgility, CommonFunctions.generateRandomDoubleBetweenRange(5, 8));
                put(PlayerAttributes.MovementBalance, CommonFunctions.generateRandomDoubleBetweenRange(3, 8));
                put(PlayerAttributes.Dribbling, CommonFunctions.generateRandomDoubleBetweenRange(3, 8));
                put(PlayerAttributes.Passing, CommonFunctions.generateRandomDoubleBetweenRange(2, 8));
                put(PlayerAttributes.AttackingCrossing, CommonFunctions.generateRandomDoubleBetweenRange(1, 8));
            }};
        return midFielderPositionAttributeFactors;
    }

    private static HashMap<PlayerAttributes, Double> getDefenderPositionFactors() {
        HashMap<PlayerAttributes, Double> defenderPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.PowerStrength, CommonFunctions.generateRandomDoubleBetweenRange(3, 5));
                put(PlayerAttributes.PowerLongShots, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.MovementSprintSpeed, CommonFunctions.generateRandomDoubleBetweenRange(4, 7));
                put(PlayerAttributes.MovementAcceleration, CommonFunctions.generateRandomDoubleBetweenRange(6, 8));
                put(PlayerAttributes.MovementAgility, CommonFunctions.generateRandomDoubleBetweenRange(3, 5));
                put(PlayerAttributes.MovementBalance, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.Defending, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
                put(PlayerAttributes.DefendingSlidingTackle, CommonFunctions.generateRandomDoubleBetweenRange(7, 10));
                put(PlayerAttributes.DefendingStandingTackle, CommonFunctions.generateRandomDoubleBetweenRange(3, 5));
            }};
        return defenderPositionAttributeFactors;
    }

    private static HashMap<PlayerAttributes, Double> getGoalKeeperPositionFactors() {
        HashMap<PlayerAttributes, Double> goalkeeperPositionAttributeFactors = new HashMap<PlayerAttributes, Double>(){
            {
                put(PlayerAttributes.PowerStrength, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.PowerLongShots, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.MovementSprintSpeed, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.MovementAcceleration, CommonFunctions.generateRandomDoubleBetweenRange(3, 6));
                put(PlayerAttributes.MovementAgility, CommonFunctions.generateRandomDoubleBetweenRange(1, 4));
                put(PlayerAttributes.MovementBalance, CommonFunctions.generateRandomDoubleBetweenRange(1, 5));
                put(PlayerAttributes.GoalkeepingDiving, CommonFunctions.generateRandomDoubleBetweenRange(2, 5));
                put(PlayerAttributes.GoalkeepingSpeed, CommonFunctions.generateRandomDoubleBetweenRange(1, 3));
                put(PlayerAttributes.GoalkeepingKicking, CommonFunctions.generateRandomDoubleBetweenRange(2, 3));
                put(PlayerAttributes.GoalkeepingHandling, CommonFunctions.generateRandomDoubleBetweenRange(1, 4));
                put(PlayerAttributes.GoalkeepingReflexes, CommonFunctions.generateRandomDoubleBetweenRange(1, 2));
            }};
        return goalkeeperPositionAttributeFactors;
    }
}
