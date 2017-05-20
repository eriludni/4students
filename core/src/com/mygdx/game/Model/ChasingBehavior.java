package com.mygdx.game.Model;

/**
 * Created by Erik on 16/05/2017.
 */
public class ChasingBehavior extends EnemyBehavior implements IEnemyBehavior {


    float X_Velocity;
    float Y_Velocity;


    public ChasingBehavior() {

        this.X_Velocity = 0;
        this.Y_Velocity = 0;
    }

    /**
     *Getter
     */
    public float getX_Velocity() {
        return X_Velocity;
    }

    /**
     *Getter
     */
    public float getY_Velocity() {
        return Y_Velocity;
    }


    @Override
    public float ApplyX_Velocity(float currentXV, float currentYV) {

            return -2f;

    }

    public float ApplyY_Velocity(float currentXV, float currentYV) {
        return 2f;
    }
}
