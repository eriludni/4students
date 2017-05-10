package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by Niklas on 2017-05-09.
 */
public class MyContactListener implements ContactListener {
    private World world;

    public MyContactListener(World world){
        this.world = world;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body body = fixtureA.getBody();
        System.out.println(fixtureA.getShape().getType().equals(fixtureB.getShape().getType()));
        if(fixtureA.getBody().isBullet()){
            libgdx_body_userdata userdata = (libgdx_body_userdata) fixtureA.getBody().getUserData();
            userdata.setForDeletion = true;
        }
        if(fixtureB.getBody().isBullet()){
            libgdx_body_userdata userdata = (libgdx_body_userdata) fixtureB.getBody().getUserData();
            userdata.setForDeletion = true;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
