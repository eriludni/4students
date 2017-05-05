package com.mygdx.game.libgdx;

import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.ICharacter;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_enemy extends libgdx_character{

    public libgdx_enemy(Enemy enemy) {
        this.character = enemy;
        defineCharacter(character);
    }

    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);
    }
}
