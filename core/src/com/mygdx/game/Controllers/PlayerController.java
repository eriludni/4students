package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.View.PlayScreen;
import com.mygdx.game.Utils.CONSTANTS;

import com.mygdx.game.LibgdxWrapper.LibgdxPlayer;
import com.mygdx.game.LibgdxWrapper.LibgdxWorld;

import java.awt.*;

/**
 * Created by Erik on 26/04/2017.
 */
public class PlayerController implements IController {

    private LibgdxWorld gameWorld;
    private LibgdxPlayer player;

    private OrthographicCamera gameCam;
    private Viewport viewPort;
    private PlayScreen playScreen;
    private Dash game;



    public PlayerController(Dash game){
        this.game = game;
        this.gameWorld = new LibgdxWorld(new GameWorld());
        this.playScreen = new PlayScreen( gameWorld);
        this.player = gameWorld.getPlayerCharacter();

        this.gameCam = playScreen.getCam();
        this.viewPort = playScreen.getViewport();

        System.out.println("NU SKAPAS JAG");
    }
    
    public void handleInput(float dt){

        if(gameWorld.getPlayerCharacter().getModel().isDead()) {
            game.setController(game.getGameOverController());
        }
        if(checkEscKeyPressed()){
            game.setController(game.getPauseController());
        }

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
        gameWorld.update(dt);
        playScreen.update(dt);
    }

    private boolean checkUpKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    private boolean checkRightKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    private boolean checkLeftKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    private boolean checkMouseButtonPressed() {
        return Gdx.input.justTouched();
    }
    private boolean checkEscKeyPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }

    private void handleMouseInput(){
        int gameCamRightPos = (int)(gameCam.position.x * CONSTANTS.PPM);
        int currentPlayerPos = gameCamRightPos - viewPort.getScreenWidth()/2;
        int x = currentPlayerPos + Gdx.input.getX();

        int y = viewPort.getScreenHeight() - Gdx.input.getY();

        Point cursorPosition = new Point(x,y);
        player.shootProjectile(cursorPosition);


    }
    public void setScreen(){
        game.setScreen(playScreen);
    }
    public LibgdxWorld getGameWorld(){
        return gameWorld;
    }
}