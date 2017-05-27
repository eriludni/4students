package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by Erik on 26/05/2017.
 *
 * @author Erik Lundin
 * Responsibility:
 * Uses: LibgdxWorld
 * Used by: LibgdxWorld
 */
public class LibgdxCloud implements Drawable {
    private int dynamicBodyID = 7;
    private LibgdxWorld world = LibgdxWorld.getlgdxWorld();
    private Body body;

    public LibgdxCloud(float x, float y, BodyDef bdf, PolygonShape shape, FixtureDef fdef){
        bdf.type = BodyDef.BodyType.StaticBody;
        bdf.position.set(x,y);

        this.body = world.getWorld().createBody(bdf);

        shape.setAsBox(16 / CONSTANTS.PPM, 16 / CONSTANTS.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData(this);
    }

    /**
     * Getter
     */
    @Override
    public float getFixtureWidth() {
        return 96 / CONSTANTS.PPM;
    }

    /**
     * Getter
     */
    @Override
    public float getFixtureHeight() {
        return 32 / CONSTANTS.PPM;
    }

    /**
     * Getter
     */
    @Override
    public int getBodyID() {
        return dynamicBodyID;
    }

}
