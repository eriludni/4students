package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Lucas on 2017-05-06.
 */
public class LibgdxEnemyBrain {
    private LibgdxEnemy lgdxEnemy;
    private final int BEHAVIOUR;




    public LibgdxEnemyBrain(LibgdxEnemy lgdxEnemy) {
        this.lgdxEnemy = lgdxEnemy;
        this.BEHAVIOUR = 2;
    }

    public void update(float dt) {

    }



    //   Move the enemy in a random direction for a random amount of time

   /* public void sporadicBehaviour() {
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

        System.out.println(lgdxEnemy.getModel().getX_velocity());
        lgdxEnemy.moveRight();
        if(lgdxEnemy.getLinearXVelocity() >= 3) {
            lgdxEnemy.getModel().reverseXVelocity();
        }
        if(lgdxEnemy.getLinearXVelocity() <= -3) {
            lgdxEnemy.getModel().reverseYVelocity();

        System.out.println(lgdxEnemy.getEnemyXVelocity());
        lgdxEnemy.moveEnemyRight(lgdxEnemy.getEnemyXVelocity());
        if (lgdxEnemy.getEnemyLinearXVelocity() >= 3) {
            lgdxEnemy.reverseEnemyXVelocity();
        }
        if (lgdxEnemy.getEnemyLinearXVelocity() <= -3) {
            lgdxEnemy.reverseEnemyXVelocity();

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


    //Decides which method, sporadicBehaviour(), linearBehaviour(), mirroredBehaviour(), that will be executed.






}
