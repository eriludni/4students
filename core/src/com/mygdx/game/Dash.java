package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screens.PlayScreen;


public class Dash extends Game {
    public SpriteBatch batch;
    private GameWorld gameWorld;

    //width of the window
    public static final int WIDTH = 1240;
    //Height of the window
    public static final int HEIGHT = 600;
    //Pixels per meter in game
    public static final float PPM = 100;

    public static final String TITLE = "DASH";


    @Override
    public void create() {
        batch = new SpriteBatch();
        gameWorld = new GameWorld(this);

        setScreen(new PlayScreen(this, gameWorld));
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
