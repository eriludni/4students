package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lucas on 2017-04-27.
 */
public class Enemy {
    private int health;
    private int V_velocity;
    private int H_velocity;
    private World world;
    private Body b2body;

    public Enemy(int health, int h_velocity, int v_velocity, GameWorld world){
        this.health = health;
        this.H_velocity = h_velocity;
        this.V_velocity = v_velocity;
        this.world = world.getWorld();
        defineEnemy();
    }

    public void defineEnemy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / Dash.PPM, 200 / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Dash.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef);
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

    public Body getBody(){
        return b2body;
    }

    public World getWorld(){
        return world;
    }
}
