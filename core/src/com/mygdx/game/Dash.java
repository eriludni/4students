package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.libgdx.libgdx_world;


public class Dash extends Game {
    public SpriteBatch batch;
    private libgdx_world gameWorld;
    private GameWorld logicalWorld;

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

        setScreen(new MenuScreen(this, gameWorld));
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
