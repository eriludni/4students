package com.mygdx.game.LibgdxWrapper;

/**
 * Created by Niklas on 2017-05-17.
 *
 * @author Niklas Baerveldt
 * Responsibility:
 * Uses:
 * Used by: PlayeScreen, LibgdxCharacter, LibgdxCloud, LibgdxGround, LibgdxPlatform, LibgdxProjectile, LibgdxPowerUp
 */
public interface Drawable {
    float getFixtureWidth();
    float getFixtureHeight();
    int getBodyID();
}
