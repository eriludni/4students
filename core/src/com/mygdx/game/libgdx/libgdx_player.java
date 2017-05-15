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
    private Player playerModel;

    public libgdx_player(Player player) {
        playerModel = player;

        defineCharacter(playerModel);
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

        getB2Body().createFixture(fdef);

        getB2Body().setUserData(this);
    }

    /*
    Checks if the player has died
     */
    public void update(float dt) {
        playerModel.checkDead();
        playerModel.setyPos(this.getB2Body().getPosition().y);
        playerModel.setxPos(this.getB2Body().getPosition().x);
        //System.out.println(playerModel.getXPos() + " : " + playerModel.getYPos());
    }

    public Player getPlayerModel() {
        return playerModel;
    }
}