package com.mygdx.game.Model;

import java.util.ArrayList;

/**
 * Created by Erik on 12/05/2017.
 */
public class LinearBehavior extends EnemyBehavior implements IEnemyBehavior {

    float X_Velocity;


    public LinearBehavior(){

        this.X_Velocity = 0;
    }

    @Override
    public float ApplyX_Velocity(float currentXV, float currentYV){
        if(currentXV < 1) {
            return 0.2f;
        }
        else{
            return 0;
        }
    }
    @Override
    public float ApplyY_Velocity(float currentXV, float currnetYV) {
        if( currentXV <= 0){
            return 2f;
        }
        else{
            return 0;
        }
    }
}
