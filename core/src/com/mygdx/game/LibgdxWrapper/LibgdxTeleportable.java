package com.mygdx.game.LibgdxWrapper;

import com.mygdx.game.Model.Teleportable;

/**
 * Created by Niklas on 2017-05-18.
 *
 * @author Niklas BaerVeldt
 * Responsibility:
 * Uses: Teleportable
 * Used by: LibgdxWorld, LibgdxCharacter, LibgdxProjectile
 */
public interface LibgdxTeleportable {
    Teleportable getModel();
    void createBodyFromModel();
}
