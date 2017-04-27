package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lucas on 2017-04-27.
 */
public class Enemy extends Character implements ICharacter{
    private int health;
    private int V_velocity;
    private int H_velocity;
    private float xPos;
    private float yPos;
    private float radius;
    private World world;

    public Enemy(int health, int h_velocity, int v_velocity, float xPos, float yPos, float radius, GameWorld world){
        this.health = health;
        this.H_velocity = h_velocity;
        this.V_velocity = v_velocity;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.world = world.getWorld();
        defineCharacter();
    }

    public void reduceHealth(int damageValue){
        health -= damageValue;
    }

    public int getHealth(){
        return health;
    }

    public int getV_velocity(){
        return V_velocity;
    }

    public int getH_velocity(){
        return H_velocity;
    }

    @Override
    public void defineCharacter() {
        super.defineCharacter(this);
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
