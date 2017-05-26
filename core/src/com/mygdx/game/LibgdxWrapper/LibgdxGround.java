package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by Niklas on 2017-05-26.
 */
public class LibgdxGround implements Drawable {
    private int dynamicBodyID = 5;
    private LibgdxWorld world = LibgdxWorld.getlgdxWorld();
    private Body body;

    public LibgdxGround(float x, float y, BodyDef bdf, PolygonShape shape, FixtureDef fdef){
        bdf.type = BodyDef.BodyType.StaticBody;
        bdf.position.set(x,y);

        this.body = world.getWorld().createBody(bdf);

        shape.setAsBox(16 / CONSTANTS.PPM, 16 / CONSTANTS.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData(this);
    }

    @Override
    public float getFixtureWidth() {
        return 32 / CONSTANTS.PPM;
    }

    @Override
    public float getFixtureHeight() {
        return 128 / CONSTANTS.PPM;
    }

    @Override
    public int getDynamicBodyID() {
        return dynamicBodyID;
    }
}
