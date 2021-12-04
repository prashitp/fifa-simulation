package com.gameplay.Substitution;

import com.gameplay.Substitution.StaminaComputation.IStaminaCalculatorController;
import com.gameplay.Substitution.StaminaComputation.StaminaObserver;

/**
 * @author Mayank Sareen
 */

public class SubstitutePlayers extends StaminaObserver {

    public SubstitutePlayers(IStaminaCalculatorController staminaCalculator){
        this.staminaCalculator = staminaCalculator;
        this.staminaCalculator.attach(this);
    }

    @Override
    public void notifyUpdate() {
        performPlayerSubstitution();
    }

    private void performPlayerSubstitution() {

    }
}

