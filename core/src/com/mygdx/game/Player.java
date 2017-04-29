package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.model.ICharacter;

/**
 * Created by Erik on 03/04/2017.
 */
public class Player extends Character implements ICharacter{

    private World world;

    private final int health;
    private float xPos;
    private float yPos;
    private float radius;
    private Vector2 velocity;

    private Texture playerSprite;

    public Player(int health, Vector2 velocity, float xPos, float yPos, float radius, GameWorld world) {
        this.health = health;
        this.velocity = velocity;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.world = world.getWorld();

        defineCharacter();
        System.out.println("player created");
    }


    /*
    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / Dash.PPM, 100 / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Dash.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef);
    }
    */

    public int getHealth() {
        return health;
    }

    public Body getB2body() {
        return b2body;
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

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }
}
