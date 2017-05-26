package com.mygdx.game.LibgdxWrapper;

import com.mygdx.game.Model.Teleportable;

/**
 * Created by Niklas on 2017-05-18.
 */
public interface LibgdxTeleportable {
    Teleportable getModel();
    void createBodyFromModel();
}
