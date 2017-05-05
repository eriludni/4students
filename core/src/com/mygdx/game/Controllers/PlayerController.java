package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Model.Player;
import com.mygdx.game.libgdx.libgdx_player;
import com.mygdx.game.libgdx.libgdx_world;

/**
 * Created by Erik on 26/04/2017.
 */
public class PlayerController {

    private World world;
    private libgdx_player player;

    public PlayerController(libgdx_world gameWorld){
        this.world = gameWorld.getWorld();
        this.player = gameWorld.getPlayerCharacter();
    }



    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.getB2body().getLinearVelocity().y <= 0)
            player.getB2body().applyLinearImpulse(new Vector2(0, 4f), player.getB2body().getWorldCenter(),true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getB2body().getLinearVelocity().x <= 2)
            player.getB2body().applyLinearImpulse(new Vector2(0.1f, 0), player.getB2body().getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getB2body().getLinearVelocity().x >= -2)
            player.getB2body().applyLinearImpulse(new Vector2(-0.1f, 0), player.getB2body().getWorldCenter(), true);

    }


}
