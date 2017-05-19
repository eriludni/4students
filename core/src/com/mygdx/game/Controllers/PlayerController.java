package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Dash;
import com.mygdx.game.libgdx.Libgdx_player;
import com.mygdx.game.libgdx.Libgdx_world;

import java.awt.*;

/**
 * Created by Erik on 26/04/2017.
 */
public class PlayerController {

    private World world;
    private Libgdx_player player;
    private OrthographicCamera gameCam;
    private Viewport viewPort;

    public PlayerController(Libgdx_world gameWorld, OrthographicCamera gameCam, Viewport viewPort){
        this.world = gameWorld.getWorld();
        this.player = gameWorld.getPlayerCharacter();
        this.gameCam = gameCam;
        this.viewPort = viewPort;
    }
    
    public void handleInput(float dt){
        if(checkUpKeyPressed()) {
            player.moveUp();
        }
        if (checkRightKeyPressed()) {
            player.moveRight();
        }
        if (checkLeftKeyPressed()) {
            player.moveLeft();
        }
        if (checkMouseButtonPressed()){
            handleMouseInput();
        }
    }

    public boolean checkUpKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    public boolean checkRightKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public boolean checkLeftKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public boolean checkMouseButtonPressed() {
        return Gdx.input.isTouched();
    }

    public void handleMouseInput(){
        int gameCamRightPos = (int)(gameCam.position.x * Dash.PPM);
        int currentPlayerPos = gameCamRightPos - viewPort.getScreenWidth()/2;
        int x = currentPlayerPos + Gdx.input.getX();

        int y = viewPort.getScreenHeight() - Gdx.input.getY();

        Point cursorPosition = new Point(x,y);
        player.shootProjectile(cursorPosition);
    }
}