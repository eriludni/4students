package com.mygdx.game.libgdx;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.Player;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_player extends libgdx_character{

    public libgdx_player(Player player) {
        this.character = player;
        defineCharacter(character);
    }

    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
    }

    public Body getB2body() {
        return b2Body;
    }
}
