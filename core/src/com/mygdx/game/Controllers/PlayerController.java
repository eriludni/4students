package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Model.Player;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.libgdx.libgdx_player;
import com.mygdx.game.libgdx.libgdx_world;

import java.awt.*;

/**
 * Created by Erik on 26/04/2017.
 */
public class PlayerController {

    private World world;
    private libgdx_player player;
    private OrthographicCamera gameCam;
    private Viewport viewPort;

    public PlayerController(libgdx_world gameWorld, OrthographicCamera gameCam, Viewport viewPort){
        this.world = gameWorld.getWorld();
        this.player = gameWorld.getPlayerCharacter();
        this.gameCam = gameCam;
        this.viewPort = viewPort;
        //this.gameCam.setToOrtho(true);
    }
    
    public void handleInput(float dt){
        if(checkUpKeyPressed() && getPlayerLinearYVelocity() >= -0.2 && getPlayerLinearYVelocity() <= 0.2) {
            movePlayerUp(4f);
        }
        if (checkRightKeyPressed() && getPlayerLinearXVelocity() <= 2) {
            movePlayerRight(0.1f);
        }
        if (checkLeftKeyPressed() && getPlayerLinearXVelocity() >= -2) {
            movePlayerLeft(-0.1f);
        }
        if (Gdx.input.isTouched()){
            player.shoot(new Point(Gdx.input.getX() + (int)(gameCam.position.x * 100) - viewPort.getScreenWidth()/2 ,viewPort.getScreenHeight() - Gdx.input.getY()));
        }
    }

    public boolean checkUpKeyPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.W);
    }

    public boolean checkRightKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public boolean checkLeftKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public float getPlayerLinearXVelocity() {
        return player.getB2Body().getLinearVelocity().x;
    }

    public float getPlayerLinearYVelocity() {
        return player.getB2Body().getLinearVelocity().y;
    }

    public void movePlayerUp(float y) {
        player.getB2Body().applyLinearImpulse(new Vector2(0, y), player.getB2Body().getWorldCenter(),true);
    }

    public void movePlayerRight(float x) {
        player.getB2Body().applyLinearImpulse(new Vector2(x, 0), player.getB2Body().getWorldCenter(), true);
    }

    public void movePlayerLeft(float x) {
        player.getB2Body().applyLinearImpulse(new Vector2(x, 0), player.getB2Body().getWorldCenter(), true);
    }

}
