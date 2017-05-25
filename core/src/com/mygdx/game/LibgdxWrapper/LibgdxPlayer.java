package com.mygdx.game.LibgdxWrapper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.Player;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by Lucas on 2017-05-05.
 */
public class LibgdxPlayer extends LibgdxCharacter {
    private Player playerModel;
    private int textureKey = 1;

    public LibgdxPlayer(Player player) {
        playerModel = player;

        defineCharacter(playerModel);
    }

    /**
     * Applies a body to the LibgdxWrapper player according to LibgdxWrapper and sets its userdata to itself so it can be identified later
     */
    @Override
    public void defineCharacter(ICharacter character) {
        super.defineCharacter(character);

        FixtureDef fdef = new FixtureDef();

        CircleShape sensor = new CircleShape();
        sensor.setRadius(character.getRadius() / CONSTANTS.PPM);

        fdef.isSensor = true;
        fdef.shape = sensor;

        getB2Body().createFixture(fdef);

        getB2Body().setUserData(this);
    }

    public void createBodyFromModel(){
        this.defineCharacter(playerModel);

        float vectorX = playerModel.getX_velocity();
        float vectorY = playerModel.getY_velocity();
        Vector2 vector2 = new Vector2(vectorX,vectorY);
        getB2Body().setLinearVelocity(vector2);
    }

    /*
    Checks if the player has died and updates model.
     */
    public void update() {
        playerModel.checkDead();
        playerModel.setyPos(this.getB2Body().getPosition().y);
        playerModel.setxPos(this.getB2Body().getPosition().x);
        playerModel.checkOutOfBounds();
        playerModel.checkxSpawnPosCrossed();
    }

    /**
     *Getter
     */
    public Player getModel() {
        return playerModel;
    }

    /**
     *Getter
     */
    public int getTextureKey() {
        return textureKey;
    }

}