package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Erik on 03/04/2017.
 */
public class Player extends Sprite {

    private World world;
    private Body b2body;

    private final int health = 3;

    private Texture playerSprite;

    public Player(GameWorld world) {
        this.world = world.getWorld();
        definePlayer();
        System.out.println("player created");
    }

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

    public int getHealth() {
        return health;
    }

    public Body getB2body() {
        return b2body;
    }

}
