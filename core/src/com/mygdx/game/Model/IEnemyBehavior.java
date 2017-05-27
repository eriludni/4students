package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 *
 * @author Erik Lundin
 * Responsibilit: Interface for enemy behaviors
 * Uses:
 * Used by: EnemyBehavior, LinearBehavior, PacingBehavior
 */
public interface IEnemyBehavior {


    float applyX_Velocity(float currentXV, float currentYV);
    float applyY_Velocity(float currentXV, float currentYV);
}
