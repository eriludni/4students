package com.mygdx.game.LibgdxWrapper;

import com.mygdx.game.Model.DynamicalBody;

/**
 * Created by Niklas on 2017-05-18.
 */
public interface Teleportable {
    DynamicalBody getModel();
    void createBodyFromModel();
}
