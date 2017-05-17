package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.PlayerController;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.libgdx.libgdx_world;


public class Dash extends Game {
    public SpriteBatch batch;
    private libgdx_world gameWorld;
    private GameWorld logicalWorld;
    private PlayerController PC;

    //width of the window
    public static final int WIDTH = 1240;
    //Height of the window
    public static final int HEIGHT = 32*20;
    //Pixels per meter in game
    public static final float PPM = 100;

    public static final String TITLE = "DASH";


    @Override
    public void create() {
        batch = new SpriteBatch();
        logicalWorld = new GameWorld();
        gameWorld = new libgdx_world(this, logicalWorld);


        setScreen(new MainMenuScreen(this, gameWorld));

    }

    public libgdx_world getGameWorld() {
        return gameWorld;
    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
        //img.dispose();
    }

}
