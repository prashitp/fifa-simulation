package com.gameplay.Substitution.StaminaComputation;

/**
 * @author Mayank Sareen
 */
public abstract class StaminaObserver {
    protected IStaminaCalculatorController staminaCalculator;
    public abstract void notifyUpdate();
}
