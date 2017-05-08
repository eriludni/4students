package com.mygdx.game.libgdx;

import com.mygdx.game.Model.Enemy;

import java.util.Random;

/**
 * Created by Lucas on 2017-05-06.
 */
public class libgdx_enemyBrain {
    private libgdx_enemy lgdxEnemy;
    private Random rand = new Random();

    public libgdx_enemyBrain(libgdx_enemy lgdxEnemy) {
        this.lgdxEnemy = lgdxEnemy;

        System.out.println("libgdx_enemy created");
    }

    public void checkCollision() {

    }

    public float checkXSpeed() {
        return lgdxEnemy.getB2Body().getLinearVelocity().x;
    }

    public void sporadicBehavior() {
        int direction = rand.nextInt(3);
        int time = (rand.nextInt(2) + 1) * 30;
        float jumpVelocity = rand.nextFloat() * 4;

        switch(direction) {
            case(0):
                if(lgdxEnemy.getEnemyLinearXVelocity() >= 2) {
                    lgdxEnemy.setEnemyLinearXVelocity(0);
                    break;
                }
                for(int i = time; i >= 0; i--) {
                    lgdxEnemy.moveEnemyRight(0.005f);
                }
                System.out.println("Right");
                break;

            case(1):
                if(lgdxEnemy.getEnemyLinearXVelocity() <= -2) {
                    lgdxEnemy.setEnemyLinearXVelocity(0);
                    break;
                }
                for(int i = time; i >= 0; i--) {
                    lgdxEnemy.moveEnemyLeft(0.005f);
                }
                System.out.println("Left");
                break;

            case(2):
                for(int i = time; i >= 0; i--) {
                    lgdxEnemy.moveEnemyUp(jumpVelocity);
                }
                System.out.println("Up");
                break;
        }
    }

    public void linearBehavior() {

    }

    public void update(float dt) {
        sporadicBehavior();
    }
}
