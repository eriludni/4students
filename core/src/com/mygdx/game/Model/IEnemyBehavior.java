package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public interface IEnemyBehavior {

    float getX_Velocity();
    float getY_Velocity();
    float UpdateX_Velocity(float currentXV);
    float UpdateY_Velocity(float currentYV);
}
