package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public class MirrorBehavior extends EnemyBehavior implements IEnemyBehavior {
    float velocity;

    public MirrorBehavior() {
    }

    /**
     *Getter
     */
    public float getVelocity() {
        return this.getVelocity();
    }


    @Override
    public float ApplyX_Velocity(float currentXV, float currentYV) {
        return 0;
    }

    @Override
    public float ApplyY_Velocity(float currentXV, float currnetYV) {
        return currnetYV;
    }
}
