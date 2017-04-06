package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Erik on 03/04/2017.
 */
public class Player extends Sprite {

    public World world;
    public Body b2body;

    private final int health = 3;

    private Texture playerSprite;

    public Player(World world) {
        this.world = world;
        definePlayer();
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(64,64);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);
        fdef.shape = shape;

        b2body.createFixture(fdef);


    }

    public int getHealth() {
        return health;
    }

}
