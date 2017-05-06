package com.mygdx.game.libgdx;

import com.mygdx.game.Model.Enemy;

/**
 * Created by Lucas on 2017-05-06.
 */
public class libgdx_enemyBrain {
    private libgdx_enemy lgdxEnemy;

    public libgdx_enemyBrain(libgdx_enemy lgdxEnemy) {
        this.lgdxEnemy = lgdxEnemy;

        System.out.println("libgdx_enemy created");
    }

    public void checkCollision() {

    }

    public float checkXSpeed() {
        return lgdxEnemy.getB2Body().getLinearVelocity().x;
    }

    public void update(float dt) {
        lgdxEnemy.moveEnemyUp(4f);
    }
}
