package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Model.DynamicalBody;

/**
 * Created by Niklas on 2017-05-18.
 */
public interface Libgdx_dynamic {
    public DynamicalBody getModel();
    public void defineBody();
}
