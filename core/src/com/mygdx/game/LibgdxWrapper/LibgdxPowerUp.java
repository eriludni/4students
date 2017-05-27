package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.PowerUp;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by lucasr on 5/18/17.
 *
 * @author Lucas Ruud
 * Responsibility: Handles the Libgdx representation of powerups
 * Uses: LibgdxWorld, PowerUp
 * Used by: LibgdxWorld, MyContactListener
 */
public class LibgdxPowerUp implements Drawable {
    private PowerUp powerUp;
    private LibgdxWorld world = LibgdxWorld.getlgdxWorld();
    private Body b2Body;
    private float radius = 10;
    private int textureKey = 4;

    LibgdxPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
        definePowerUp();
    }

    /**
     * Applies a body to the Libgdx powerup according to ligdx and sets its userdata to itself so it can be identified later
     */
    private void definePowerUp() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(powerUp.getxPos() / CONSTANTS.PPM, powerUp.getyPos() / CONSTANTS.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / CONSTANTS.PPM);
        fdef.isSensor = true;
        fdef.shape = shape;
        b2Body.createFixture(fdef);


        b2Body.setUserData(this);
    }

    /**
     * Getter
     */
    public PowerUp getLogicalPowerUp() {
        return powerUp;
    }

    /**
     * Getter
     */
    public Body getB2Body() {
        return b2Body;
    }

    /**
     * Getter
     */
    @Override
    public float getFixtureWidth() {
        return b2Body.getFixtureList().get(0).getShape().getRadius() * 2;
    }

    /**
     * Getter
     */
    @Override
    public float getFixtureHeight() {
        return b2Body.getFixtureList().get(0).getShape().getRadius() * 2;
    }

    /**
     * Getter
     */
    @Override
    public int getBodyID() {
        return textureKey;
    }
}
