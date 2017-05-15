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

    private void setVelocity(){
        if (this.X_Velocity == 0 ){
            X_Velocity = 3;
        }
        else if (this.X_Velocity > 0){
            X_Velocity = -3;
        }
        else if (this.X_Velocity < 0){
            X_Velocity = 3;
        }
    }
}
