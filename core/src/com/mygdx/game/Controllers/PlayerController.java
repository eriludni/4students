package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Model.Player;
import com.mygdx.game.libgdx.Dash;
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
        if(player.getB2Body() != null && checkUpKeyPressed() && getPlayerLinearYVelocity() == 0) {
            movePlayerUp(6f);
        }
        if (player.getB2Body() != null && checkRightKeyPressed() && getPlayerLinearXVelocity() <= 2) {
            movePlayerRight(0.1f);
        }
        if (player.getB2Body() != null && checkLeftKeyPressed() && getPlayerLinearXVelocity() >= -2) {
            movePlayerLeft(-0.1f);
        }
        if (Gdx.input.isTouched()){
            shootPlayerProjectile();
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

    public float getPlayerLinearXVelocity() {
        return player.getB2Body().getLinearVelocity().x;
    }

    public float getPlayerLinearYVelocity() {
        return player.getB2Body().getLinearVelocity().y;
    }

    public void shootPlayerProjectile(){
        int gameCamRightPos = (int)(gameCam.position.x * Dash.PPM);
        int currentPlayerPos = gameCamRightPos - viewPort.getScreenWidth()/2;
        int x = currentPlayerPos + Gdx.input.getX();

        int y = viewPort.getScreenHeight() - Gdx.input.getY();

        Point shootingPoint = new Point(x,y);
        player.shootProjectile(shootingPoint);
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
