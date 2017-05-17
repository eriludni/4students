package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.MainMenuScreen;

/**
 * Created by lucasr on 5/17/17.
 */
public class GameOverController {

    private Viewport viewPort;
    GameOverScreen gameOverScreen;

    public GameOverController(Viewport viewPort, GameOverScreen gameOverScreen){
        this.gameOverScreen = gameOverScreen;
        this.viewPort = viewPort;

    }
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(gameOverScreen.getStage());

    }
}
