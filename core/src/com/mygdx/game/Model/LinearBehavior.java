package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public class LinearBehavior extends EnemyBehavior {

    private float x_Velocity;


    public LinearBehavior(){

        this.x_Velocity = 0;
    }


    /**
     * updates the x-velocity
     */
    @Override
    public float applyX_Velocity(float currentXV, float currentYV){
        if(currentXV > -2) {
            return -0.2f;
        }
        else{
            return 0;
        }
    }
    /**
     * updates the y-velocity
     */
    @Override
    public float applyY_Velocity(float currentXV, float currnetYV) {
        if( currentXV >= 0){
            return 0.5f;
        }
        else{
            return 0;
        }
    }
}
