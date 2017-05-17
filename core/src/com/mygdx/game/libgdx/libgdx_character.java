package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Dash;
import com.mygdx.game.Model.*;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * Created by Lucas on 2017-05-05.
 */
public abstract class libgdx_character{
    private Body b2Body;
    private libgdx_world world = libgdx_world.getlgdxWorld();
    private Fixture fixture;

    private float maxVelocity = 2;
    private float horizontalAcceleration = 0.1f;
    private float verticalAcceleration = 5f;

    /*
    Applies a Body to the specified character
     */
    public void defineCharacter(ICharacter character) {
        BodyDef bdef = new BodyDef();
        bdef.position.set( character.getXPos() / Dash.PPM, character.getYPos() / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(character.getRadius() / Dash.PPM);
        fdef.shape = shape;

        fixture = b2Body.createFixture(fdef);
    }

    /*
    Creates a projectile and launches it towards a point indicated by the mouse
     */
    public void shootProjectile(Point targetPosition) {
            int x = (int) (getB2Body().getWorldCenter().x * 100);
            int y = (int) (getB2Body().getWorldCenter().y * 100);
            Point playerPosition = new Point(x, y);
            new libgdx_projectile(playerPosition, targetPosition, new Projectile(5,20));
    }

    /*
    Move the character upwards with a force specified by the variable verticalAcceleration.
     */
    public void moveUp() {
        if(getLinearYVelocity() <= 0.2  && getLinearYVelocity() >= -0.2)
        b2Body.applyLinearImpulse(new Vector2(0, verticalAcceleration), b2Body.getWorldCenter(),true);
    }

    /*
    Move the character rightwards with a force specified by the variable horizontalAcceleration.
     */
    public void moveRight() {
        if(b2Body != null && getLinearXVelocity() <= maxVelocity) {
            b2Body.applyLinearImpulse(new Vector2(horizontalAcceleration, 0), b2Body.getWorldCenter(), true);
        }
    }

    /*
    Move the character leftwards with a force specified by the variable horizontalAcceleration.
     */
    public void moveLeft() {
        if(b2Body != null && getLinearXVelocity() <= maxVelocity) {
            b2Body.applyLinearImpulse(new Vector2(-horizontalAcceleration, 0), b2Body.getWorldCenter(), true);
        }
    }

    /*
    Get the linear velocity in the x-axis for the character
     */
    public float getLinearXVelocity() {
        return b2Body.getLinearVelocity().x;
    }

    /*
    Get the linear velocity in the y-axis for the character
     */
    public float getLinearYVelocity() {
        return b2Body.getLinearVelocity().y;
    }

    public Body getB2Body() {
        return b2Body;
    }
}
