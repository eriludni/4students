package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 *
 * @author Erik Lundin
 * Responsibility: Decides what behavior a enemy is assigned
 * Uses: IEnemyBehavior, LinearBehavior, PacingBehavior
 * Used by: EnemyBrain, LinearBehavior, PacingBehavior
 */
public class EnemyBehavior implements IEnemyBehavior {

    private IEnemyBehavior behavior;


    public EnemyBehavior() {
    }


    public EnemyBehavior(int id) {

        /**
         * Decides what behavior an enemy will have
         */

        switch (id) {

            case 0:
                this.behavior = new LinearBehavior();
                break;
            case 1:
                this.behavior = new PacingBehavior();
                break;
            default:
                this.behavior = new LinearBehavior();
                break;
        }


    }


    /**
     * Getter
     */
    public float getX_Velocity() {
        return 0;
    }

    /**
     * Getter
     */
    public float getY_Velocity() {
        return 0;
    }

    /**
     * return updated velocity
     */
    public float applyX_Velocity(float currentXV, float currentYV) {
        return behavior.applyX_Velocity(currentXV, currentYV);
    }
    /**
     * return updated velocity
     */

    public float applyY_Velocity(float currentXV, float currentYV) {
        return behavior.applyY_Velocity(currentXV, currentYV);
    }
}
