package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lucas on 2017-04-27.
 */
public class Enemy extends Character implements ICharacter{
    private int health;
    private float xPos;
    private float yPos;
    private float radius;
    private World world;
    private Vector2 velocity;

    public Enemy(int health, Vector2 velocity, float xPos, float yPos, float radius, GameWorld world){
        this.health = health;
        this.velocity = velocity;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.world = world.getWorld();
        defineCharacter();
    }

    public void reduceHealth(int damageValue){
        health -= damageValue;
    }

    public boolean isDead() {
        if (getHealth() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void reverseXVelocity() {
        velocity.x = -velocity.x;
    }

    public void reverseYVelocity() {
        velocity.y = -velocity.y;
    }

    public int getHealth(){
        return health;
    }

    public void update(float dt) {
        b2body.setLinearVelocity(velocity);
    }

    @Override
    public void defineCharacter() {
        super.defineCharacter(this);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public float getXPos() {
        return xPos;
    }

    @Override
    public float getYPos() {
        return yPos;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public float getRadius() {
        return radius;
    }
}
