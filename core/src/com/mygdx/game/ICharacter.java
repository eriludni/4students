package com.mygdx.game;

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
}
