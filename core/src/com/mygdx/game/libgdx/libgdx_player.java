package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.IKillable;
import com.mygdx.game.Model.Player;

import java.awt.*;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_player extends libgdx_character{
    private ICharacter character;
    private IKillable killableCharacter;

    public libgdx_player(Player player) {
        character = player;
        killableCharacter = player;
        //this.health = player.getHealth();

        defineCharacter(character);

        System.out.println("libgdx_player created");
        System.out.println(b2Body.getUserData());
    }

    /*
    Applies a Body to the player, by using the method defineCharacter() in its super class and sets its userdata to itself, so it can be identified later
     */
    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);

        FixtureDef fdef = new FixtureDef();

        CircleShape sensor = new CircleShape();
        sensor.setRadius(character.getRadius() / Dash.PPM);

        fdef.isSensor = true;
        fdef.shape = sensor;

        fixture = b2Body.createFixture(fdef);

        b2Body.setUserData(this);
    }

    /*
    Checks if the player has died
     */
    public void update(float dt) {
        killableCharacter.checkDead();
    }

    public ICharacter getCharacter() {
        return character;
    }

    public IKillable getKillableCharacter() {
        return killableCharacter;
    }
}