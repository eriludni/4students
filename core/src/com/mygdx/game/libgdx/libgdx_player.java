package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.Player;

import java.awt.*;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_player extends libgdx_character{

    public libgdx_player(Player player) {
        this.character = player;
        this.health = player.getHealth();

        defineCharacter(character);
    }

    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);

        FixtureDef fdef = new FixtureDef();

        CircleShape sensor = new CircleShape();
        sensor.setRadius(character.getRadius() / Dash.PPM);

        fdef.isSensor = true;
        fdef.shape = sensor;

        fixture = b2Body.createFixture(fdef);

        fixture.setUserData(this);
    }

    public void update(float dt) {
        this.checkDead();
    }

    public void dispose() {
        this.dispose();
    }
}