package com.mygdx.game.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Controllers.Dash;
import com.mygdx.game.Model.*;
import com.mygdx.game.Utils.CONSTANTS;

import java.awt.*;

import static java.lang.Math.abs;

/**
 * Created by Lucas on 2017-05-05.
 */
public abstract class libgdx_character implements TextureObject, Libgdx_dynamic{
    private Body b2Body;
    private libgdx_world world = libgdx_world.getlgdxWorld();
    private Fixture fixture;

    private Texture texture = new Texture("player.png");

    private float maxVelocity = 2;
    private float minVelocity = -2;
    private float horizontalAcceleration = 0.1f;
    private float verticalAcceleration = 5f;

    /**
     * Applies a body to the character according to libgdx
     */
    public void defineCharacter(ICharacter character) {
        BodyDef bdef = new BodyDef();
        bdef.position.set( character.getXPos() / CONSTANTS.PPM, character.getYPos() / CONSTANTS.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(character.getRadius() / CONSTANTS.PPM);
        fdef.shape = shape;

        fixture = b2Body.createFixture(fdef);
    }

    /**
     * Getter
     */
    public Texture getTexture(){
        return texture;
    }

    /**
     * Getter
     */
    public float getSize(){
        return b2Body.getFixtureList().get(0).getShape().getRadius();
    }

    /**
     * Creates a projectile and launches it towards a point indicated by the mouse
     */
    public void shootProjectile(Point targetPosition) {
            int x = (int) (getB2Body().getWorldCenter().x * 100);
            int y = (int) (getB2Body().getWorldCenter().y * 100);
            Point playerPosition = new Point(x, y);
            new libgdx_projectile(playerPosition, targetPosition, new Projectile(5,20));
    }

    /**
     * Move the character upwards with a force specified by the variable verticalAcceleration.
     */
    public void moveUp() {
        if(getLinearYVelocity() == 0) {
            b2Body.applyLinearImpulse(new Vector2(0, verticalAcceleration), b2Body.getWorldCenter(), true);
        }
    }

    /**
     * Move the character rightwards with a force specified by the variable horizontalAcceleration.
     */
    public void moveRight() {
        if(b2Body != null && getLinearXVelocity() <= maxVelocity) {
            b2Body.applyLinearImpulse(new Vector2(horizontalAcceleration, 0), b2Body.getWorldCenter(), true);
        }
    }

    /**
     * Move the character leftwards with a force specified by the variable horizontalAcceleration.
     */
    public void moveLeft() {
        if(b2Body != null && getLinearXVelocity() >= minVelocity) {
            b2Body.applyLinearImpulse(new Vector2(-horizontalAcceleration, 0), b2Body.getWorldCenter(), true);
        }
    }

    /**
     *Getter
     */
    public float getLinearXVelocity() {
        return b2Body.getLinearVelocity().x;
    }

    /**
     *Getter
     */
    public float getLinearYVelocity() {
        return b2Body.getLinearVelocity().y;
    }

    /**
     *Getter
     */
    public Body getB2Body() {
        return b2Body;
    }
}
