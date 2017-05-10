package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.Character;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.Projectile;

import java.awt.*;

/**
 * Created by Lucas on 2017-05-05.
 */
public abstract class libgdx_character extends Character{

    protected ICharacter character;
    protected Body b2Body;
    protected libgdx_world world = libgdx_world.getlgdxWorld();

    protected int health;
    protected float xPos;
    protected float yPos;
    protected float radius;
    protected float x_velocity;
    protected float y_velocity;
    protected boolean dead;
    protected boolean airBorn;
    protected boolean toBeRemoved;

    public void defineCharacter(ICharacter character) {
        BodyDef bdef = new BodyDef();
        bdef.position.set( character.getXPos() / Dash.PPM, character.getYPos() / Dash.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.getWorld().createBody(bdef);
        libgdx_body_userdata userdata = new libgdx_body_userdata();
        b2Body.setUserData(userdata);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(character.getRadius() / Dash.PPM);
        fdef.shape = shape;

        b2Body.createFixture(fdef);
    }

    public void shootProjectile(Point targetPosition) {
        int x = (int)(getB2Body().getWorldCenter().x * 100);
        int y = (int)(getB2Body().getWorldCenter().y * 100 + 20);
        Point playerPosition = new Point(x,y);
        new libgdx_projectile(playerPosition, targetPosition, 5);
    }

    public Body getB2Body() {
        return b2Body;
    }
}
