package com.mygdx.game.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.PlayerController;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.libgdx.libgdx_world;


public class Dash extends Game {

    private IController currentController;
    private MenuController mainMenuController;
    private GameOverController gameOverController;
    private PlayerController playerController;


    //width of the window
    public static final int WIDTH = 1240;
    //Height of the window
    public static final int HEIGHT = 32*20;
    //Pixels per meter in game
    public static final float PPM = 100;

    public static final String TITLE = "DASH";

    private Music music;


    @Override
    public void create() {

        /*
        //musicPlaceHolder
        music = Gdx.audio.newMusic(Gdx.files.internal("The Proclaimers - I'm Gonna Be (500 Miles) Lyrics.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();//musicPlaceHolder
        */

        mainMenuController = new MenuController(this);
        playerController = new PlayerController(this);
        gameOverController = new GameOverController(this);

        currentController = mainMenuController;

    }

    //public libgdx_world getGameWorld() {
    //    return gameWorld;
    //}
    public MenuController getMainMenuController() {return mainMenuController;}
    public PlayerController getPlayerController(){return playerController;}
    public GameOverController getGameOverController(){return  gameOverController;}

    @Override
    public void render() {

        super.render();
        this.currentController.handleInput(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }

    public void setController(IController currentController){

        this.currentController = currentController;
        this.currentController.setScreen();


    }
}
