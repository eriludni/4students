package com.mygdx.game.Model;

/**
 * Created by Erik on 12/05/2017.
 */
public class MirrorBehavior extends EnemyBehavior implements IEnemyBehavior {
    float velocity;
    public MirrorBehavior(){
    this.velocity = 0;
    }

    public float getVelocity() {
        return this.getVelocity();
    }

    public void setVelocity(){
    }
}
