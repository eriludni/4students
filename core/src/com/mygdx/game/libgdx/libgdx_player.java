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

        System.out.println("libgdx_player created");
        System.out.println(b2Body.getUserData());
    }

    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
        b2Body.setUserData("Player");
    }
}
