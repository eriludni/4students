package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public class EnemyBehavior implements IEnemyBehavior {

    IEnemyBehavior behavior;
    Enemy enemy;


    public EnemyBehavior() {
    }


    public EnemyBehavior(Enemy enemy, int id) {
        this.enemy = enemy;

        switch (id) {
            case 0:
                this.behavior = new LinearBehavior();
                break;
            case 1:
                this.behavior = new PacingBehavior();
                break;
            case 2:
                this.behavior = new ChasingBehavior();
                break;
            case 3:
                this.behavior = new MirrorBehavior();
                break;
        }


    }


    /**
     *Getter
     */
    public float getX_Velocity() {
        return 0;
    }

    /**
     *Getter
     */
    public float getY_Velocity() {
        return 0;
    }


    public float ApplyX_Velocity(float currentXV, float currentYV) {
        return behavior.ApplyX_Velocity(currentXV, currentYV);
    }

    public float ApplyY_Velocity(float currentXV, float currentYV) {
        return behavior.ApplyY_Velocity(currentXV, currentYV);
    }
}
