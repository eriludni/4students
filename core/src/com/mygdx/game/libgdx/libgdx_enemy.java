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

        this.health = character.getHealth();
        this.xPos = character.getXPos();
        this.yPos = character.getYPos();
        this.radius = character.getRadius();
        this.x_velocity = character.getX_velocity();
        this.y_velocity = character.getY_velocity();
        this.dead = character.isDead();
        this.airBorn = character.isAirBorn();
        this.toBeRemoved = character.getToBeRemoved();

        defineCharacter(character);

        System.out.println("libgdx_enemy created");
        System.out.println(b2Body.getUserData());
    }

    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
        b2Body.setUserData("Enemy");
    }

    public void moveEnemyUp(float y) {
        if(getEnemyLinearYVelocity() == 0) {
            getB2Body().applyLinearImpulse(new Vector2(0, y), getB2Body().getWorldCenter(),true);
        }
    }

    public void moveEnemyRight(float x) {
        getB2Body().applyLinearImpulse(new Vector2(x, 0), getB2Body().getWorldCenter(),true);
    }

    public void moveEnemyLeft(float x) {
        getB2Body().applyLinearImpulse(new Vector2(-x, 0), getB2Body().getWorldCenter(),true);
    }

    public float getEnemyLinearYVelocity() {
        return getB2Body().getLinearVelocity().y;
    }

    public float getEnemyLinearXVelocity() {
        return getB2Body().getLinearVelocity().x;
    }

    public void setEnemyLinearXVelocity(float x) {
        getB2Body().setLinearVelocity(x, getB2Body().getLinearVelocity().y);
    }

    public void setEnemyLinearYVelocity(float y) {
        getB2Body().setLinearVelocity(getB2Body().getLinearVelocity().x, y);
    }

    public float getEnemyXVelocity() {
        return this.x_velocity;
    }

    public void reverseEnemyXVelocity() {
        this.x_velocity = -this.x_velocity;
    }
}
