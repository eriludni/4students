package com.mygdx.game.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Model.Enemy;

import java.util.Random;

/**
 * Created by Lucas on 2017-05-06.
 */
public class libgdx_enemyBrain {
    private libgdx_enemy lgdxEnemy;
    private final int BEHAVIOR;
    private Random rand = new Random();

    public libgdx_enemyBrain(libgdx_enemy lgdxEnemy) {
        this.lgdxEnemy = lgdxEnemy;
        this.BEHAVIOR = 2;
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
        System.out.println(lgdxEnemy.getEnemyXVelocity());
        lgdxEnemy.moveEnemyRight(lgdxEnemy.getEnemyXVelocity());
        if(lgdxEnemy.getEnemyLinearXVelocity() >= 3) {
            lgdxEnemy.reverseEnemyXVelocity();
        }
        if(lgdxEnemy.getEnemyLinearXVelocity() <= -3) {
            lgdxEnemy.reverseEnemyXVelocity();
        }
    }

    public void mirroredBehavior() {
        if(checkUpKeyPressed() && lgdxEnemy.getEnemyLinearYVelocity() == 0) {
            lgdxEnemy.moveEnemyUp(4f);
        }
        if(checkRightKeyPressed() && lgdxEnemy.getEnemyLinearXVelocity() >= -2) {
            lgdxEnemy.moveEnemyLeft(0.1f);
        }
        if(checkLeftKeyPressed() && lgdxEnemy.getEnemyLinearXVelocity() <= 2) {
            lgdxEnemy.moveEnemyRight(0.1f);
        }
    }

    public boolean checkUpKeyPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.W);
    }

    public boolean checkRightKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public boolean checkLeftKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public void update(float dt) {
        switch(BEHAVIOR) {
            case(0):
                linearBehavior();
                break;

            case(1):
                sporadicBehavior();
                break;

            case(2):
                mirroredBehavior();
                break;
        }
    }
}
