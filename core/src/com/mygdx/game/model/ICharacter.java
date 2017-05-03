package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Lucas on 2017-04-27.
 */
public interface ICharacter {
    void defineCharacter();
    float getXPos();
    float getYPos();
    World getWorld();
    float getRadius();
    Vector2 getVelocity();
}