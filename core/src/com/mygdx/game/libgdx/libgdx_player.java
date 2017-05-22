package com.mygdx.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Model.ICharacter;
import com.mygdx.game.Model.Player;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_player extends libgdx_character{
    private Player playerModel;

    public libgdx_player(Player player) {
        playerModel = player;

        defineCharacter(playerModel);

        System.out.println("libgdx player created");
    }

    /**
     * Applies a body to the libgdx player according to libgdx and sets its userdata to itself so it can be identified later
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
        System.out.println("playerPosition: " + playerModel.getXPos());
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

    public void dispose() {
        this.dispose();
    }

    /**
     *Setter
     */
    public void setPlayerModel(Player player) {
        this.playerModel = player;
    }
}