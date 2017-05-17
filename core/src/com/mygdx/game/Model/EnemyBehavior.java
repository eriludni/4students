package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public class EnemyBehavior implements IEnemyBehavior {

    IEnemyBehavior behavior;


    public EnemyBehavior() {
    }


    public EnemyBehavior(int id) {
        switch (id) {
            case 0:
                this.behavior = new LinearBehavior();
                break;
            case 1:
                this.behavior = new ChasingBehavior();
                break;
            case 2:
                break;
        }


    }


    public float getX_Velocity() {
        return 0;
    }

    public float getY_Velocity() {
        return 0;
    }



    public float ApplyX_Velocity(float currentXV, float currentYV) {
        return behavior.ApplyX_Velocity(currentXV, currentYV);
    }
    public float ApplyY_Velocity(float currentXV, float currentYV) {
        return behavior.ApplyY_Velocity(currentXV, currentYV );
    }
}
