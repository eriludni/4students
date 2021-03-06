package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.*;
import com.mygdx.game.Model.Character;
import com.mygdx.game.Utils.CONSTANTS;
import java.awt.*;

/**
 * Created by Lucas on 2017-05-05.
 *
 * @author Lucas Ruud
 * Responsibility: Handles general functions that all Libgdx characters should have
 * Uses: LibgdxTeleportable, CONSTANTS
 * Used by: LibgdxEnemy, LibgdxPlayer
 */
public abstract class LibgdxCharacter implements Drawable, LibgdxTeleportable {
    private Body b2Body;
    private LibgdxWorld world = LibgdxWorld.getlgdxWorld();
    private Fixture fixture;
    private Character characterModel;

    /**
     * Applies a body to the character according to Libgdx
     */
    public void defineCharacter(Character character) {
        characterModel = character;
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
     * Creates a projectile and launches it towards a point indicated by the mouse
     */
    public void shootProjectile(Point targetPosition) {
            int x = (int) (getB2Body().getWorldCenter().x * 100);
            int y = (int) (getB2Body().getWorldCenter().y * 100);
            Point playerPosition = new Point(x, y);
            new LibgdxProjectile(playerPosition, targetPosition, new Projectile(5,20),getFixtureWidth());
    }

    /**
     * Move the character upwards with a force specified by the variable verticalAcceleration.
     */
    public void moveUp() {
        if(getLinearYVelocity() == 0) {
            b2Body.applyLinearImpulse(new Vector2(0, characterModel.getVerticalAcceleration()), b2Body.getWorldCenter(), true);
        }
    }

    /**
     * Move the character rightwards with a force specified by the variable horizontalAcceleration.
     */
    public void moveRight() {
        if(b2Body != null && getLinearXVelocity() <= characterModel.getMaxVelocity()) {
            b2Body.applyLinearImpulse(new Vector2(characterModel.getHorizontalAcceleration(), 0), b2Body.getWorldCenter(), true);
        }
    }

    /**
     * Move the character leftwards with a force specified by the variable horizontalAcceleration.
     */
    public void moveLeft() {
        if(b2Body != null && getLinearXVelocity() >= characterModel.getMinVelocity()) {
            b2Body.applyLinearImpulse(new Vector2(-characterModel.getHorizontalAcceleration(), 0), b2Body.getWorldCenter(), true);
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

    /**
     * Getter
     */
    public float getFixtureWidth(){
        return b2Body.getFixtureList().get(0).getShape().getRadius() * 2;
    }

    /**
     * Getter
     */
    public float getFixtureHeight(){
        return b2Body.getFixtureList().get(0).getShape().getRadius() * 2;
    }
}
