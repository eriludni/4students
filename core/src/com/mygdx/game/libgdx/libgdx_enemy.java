package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_enemy extends libgdx_character{

    public libgdx_enemy(Enemy enemy) {
        this.character = enemy;
        this.killableCharacter = enemy;

        defineCharacter(character);

        System.out.println("libgdx_enemy created");
        System.out.println(b2Body.getUserData());
    }

    /*
    Applies a Body to the enemy, by using the method defineCharacter() in its super class and sets its userdata to itself, so it can be identified later
     */
    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
        b2Body.setUserData(this);
    }

    /*
    Move the enemy upwards with a specified force
     */
    public void moveEnemyUp(float y) {
        if(getEnemyLinearYVelocity() == 0) {
            getB2Body().applyLinearImpulse(new Vector2(0, y), getB2Body().getWorldCenter(),true);
        }
    }

    /*
    Move the enemy rightwards with a specified force
     */
    public void moveEnemyRight(float x) {
        getB2Body().applyLinearImpulse(new Vector2(x, 0), getB2Body().getWorldCenter(),true);
    }

    /*
    Move the enemy leftwards with a specified force
     */
    public void moveEnemyLeft(float x) {
        getB2Body().applyLinearImpulse(new Vector2(-x, 0), getB2Body().getWorldCenter(),true);
    }

    /*
    Get the linear velocity in the y-axis for the enemy
     */
    public float getEnemyLinearYVelocity() {
        return getB2Body().getLinearVelocity().y;
    }

    /*
    Get the linear velocity in the x-axis for the enemy
     */
    public float getEnemyLinearXVelocity() {
        return getB2Body().getLinearVelocity().x;
    }

    /*
    Set the linear velocity in the x-axis for the enemy
     */
    public void setEnemyLinearXVelocity(float x) {
        getB2Body().setLinearVelocity(x, getB2Body().getLinearVelocity().y);
    }

    /*
    Set the linear velocity in the y-axis for the enemy
     */
    public void setEnemyLinearYVelocity(float y) {
        getB2Body().setLinearVelocity(getB2Body().getLinearVelocity().x, y);
    }

    public float getEnemyXVelocity() {
        return character.getX_velocity();
    }

    public void reverseEnemyXVelocity() {
        character.reverseXVelocity();
    }

    public void dispose() {
        this.dispose();
    }

    /*
    Checks if the enemy has died
     */
    public void update(float dt) {
        this.checkDead();

        if(isDead()) {
            //System.out.println("Enemy died");
        }
    }
}
