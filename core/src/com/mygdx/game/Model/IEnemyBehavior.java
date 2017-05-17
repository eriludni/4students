package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public interface IEnemyBehavior {


    float ApplyX_Velocity(float currentXV, float currentYV);
    float ApplyY_Velocity(float currentXV, float currentYV);
}
