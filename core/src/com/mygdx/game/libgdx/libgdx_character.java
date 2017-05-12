package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.*;
import com.mygdx.game.Model.Character;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Lucas on 2017-05-05.
 */
public abstract class libgdx_character{

    protected ICharacter character;
    protected IKillable killableCharacter;
    protected Body b2Body;
    protected libgdx_world world = libgdx_world.getlgdxWorld();
    protected Fixture fixture;

    /*
    Applies a Body to the specified character
     */
    public void defineCharacter(ICharacter character) {
        BodyDef bdef = new BodyDef();
        bdef.position.set( character.getXPos() / Dash.PPM, character.getYPos() / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);

        //libgdx_body_userdata userdata = new libgdx_body_userdata();
        //b2Body.setUserData(userdata);

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
            int y = (int) (getB2Body().getWorldCenter().y * 100 + 20);
            Point playerPosition = new Point(x, y);
            new libgdx_projectile(playerPosition, targetPosition, 5);
    }

    public Body getB2Body() {
        return b2Body;
    }

    public int getHealth() {
        return killableCharacter.getHealth();
    }

    public void setHealth(int health) {
        killableCharacter.setHealth(health);
    }

    public void reduceHealth(int damageValue) {
        killableCharacter.reduceHealth(damageValue);
    }

    public void setDead(boolean dead) {
        killableCharacter.setDead(dead);
    }

    public boolean isDead() {
        return killableCharacter.isDead();
    }

    public void checkDead() {
        killableCharacter.checkDead();
    }
}
