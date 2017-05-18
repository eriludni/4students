package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Dash;
import com.mygdx.game.Model.PowerUp;

/**
 * Created by lucasr on 5/18/17.
 */
public class libgdx_powerUp {
    private PowerUp powerUp;
    private libgdx_world world = libgdx_world.getlgdxWorld();
    private Body b2Body;
    private float radius = 10;

    libgdx_powerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
        definePowerUp();
        System.out.println("PowerUp");
    }

    public void definePowerUp() {
        BodyDef bdef = new BodyDef();
        bdef.position.set( powerUp.getxPos() / Dash.PPM, powerUp.getyPos() / Dash.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / Dash.PPM);
        fdef.isSensor = true;
        fdef.shape = shape;
        b2Body.createFixture(fdef);

        b2Body.setUserData(this);
    }

    public PowerUp getLogicalPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public Body getB2Body() {
        return b2Body;
    }

    public void dispose() {
        this.dispose();
    }
}
