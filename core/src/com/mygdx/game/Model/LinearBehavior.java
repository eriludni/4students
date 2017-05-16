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


    public float getVelocity() {
         return X_Velocity;
    }


    @Override
    public float UpdateX_Velocity(float currentXV){
        if (currentXV == 0 ){
            //System.out.println(currentXV);
            return 0;
        }
        else if (currentXV >= 3){
            //System.out.println(currentXV);
            return  0;
        }
        else if (currentXV <= -3){
            //System.out.println(currentXV);
           return  0;
        }
        System.out.println("NÅGOT ÄR FEL");
        return 0;
    }
}
