package com.mygdx.game.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.Random;

/**
 * Created by Lucas on 2017-05-06.
 */
public class libgdx_enemyBrain {
    private libgdx_enemy lgdxEnemy;
    private final int BEHAVIOUR;
    private Random rand = new Random();

    public libgdx_enemyBrain(libgdx_enemy lgdxEnemy) {
        this.lgdxEnemy = lgdxEnemy;
        this.BEHAVIOUR = 2;
    }

    /*
       Move the enemy in a random direction for a random amount of time
     */
    public void sporadicBehaviour() {
        int direction = rand.nextInt(3);
        int time = (rand.nextInt(2) + 1) * 30;
        float jumpVelocity = rand.nextFloat() * 4;

        switch(direction) {
            case(0):
                if(lgdxEnemy.getLinearXVelocity() >= 2) {
                    lgdxEnemy.setEnemyLinearXVelocity(0);
                    break;
                }
                for(int i = time; i >= 0; i--) {
                    lgdxEnemy.moveRight();
                }
                System.out.println("Right");
                break;

            case(1):
                if(lgdxEnemy.getLinearXVelocity() <= -2) {
                    lgdxEnemy.setEnemyLinearXVelocity(0);
                    break;
                }
                for(int i = time; i >= 0; i--) {
                    lgdxEnemy.moveLeft();
                }
                System.out.println("Left");
                break;

            case(2):
                for(int i = time; i >= 0; i--) {
                    lgdxEnemy.moveUp();
                }
                System.out.println("Up");
                break;
        }
    }


    public void linearBehaviour() {
        System.out.println(lgdxEnemy.getEnemyModel().getX_velocity());
        lgdxEnemy.moveRight();
        if(lgdxEnemy.getLinearXVelocity() >= 3) {
            lgdxEnemy.getEnemyModel().reverseXVelocity();
        }
        if(lgdxEnemy.getLinearXVelocity() <= -3) {
            lgdxEnemy.getEnemyModel().reverseYVelocity();
        }
    }

    /*
    Move the enemy in the opposite direction the player movement, except for up.
     */
    public void mirroredBehaviour() {
        if(checkUpKeyPressed() && lgdxEnemy.getLinearYVelocity() == 0) {
            lgdxEnemy.moveUp();
        }
        if(checkRightKeyPressed() && lgdxEnemy.getLinearXVelocity() >= -2) {
            lgdxEnemy.moveLeft();
        }
        if(checkLeftKeyPressed() && lgdxEnemy.getLinearXVelocity() <= 2) {
            lgdxEnemy.moveRight();
        }
    }

    /*
    Checks if the key used for upwards movement has been pressed
     */
    public boolean checkUpKeyPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.W);
    }

    /*
    Checks if the key used for rightwards movement has been pressed
     */
    public boolean checkRightKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    /*
    Checks if the key used for leftwards movement has been pressed
     */
    public boolean checkLeftKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    /*
    Decides which method, sporadicBehaviour(), linearBehaviour(), mirroredBehaviour(), that will be executed.
     */
    public void update(float dt) {
        switch(BEHAVIOUR) {
            case(0):
                linearBehaviour();
                break;

            case(1):
                sporadicBehaviour();
                break;

            case(2):
                mirroredBehaviour();
                break;
        }
    }
}
