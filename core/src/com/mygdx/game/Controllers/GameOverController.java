package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.View.GameOverScreen;

/**
 * Created by lucasr on 5/17/17.
 */
public class GameOverController implements IController {

    private GameOverScreen gameOverScreen;
    private Dash game;

    public GameOverController(Dash game){
        this.game = game;
        this.gameOverScreen = new GameOverScreen(game.getPlayerController().getGameWorld().getLogicalWorld());
        setListeners();
    }
    /**
     * Update method for controller
     */
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(this.gameOverScreen.getStage());
        this.gameOverScreen.update(dt);
    }

    /**
     *apply listeners for menubuttons
     */
    private void setListeners(){
        this.gameOverScreen.getStage().getActors().get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getNewPlayerController());
            }
        });
        this.gameOverScreen.getStage().getActors().get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    /**
     * Setter
     */
    public void setScreen(){
        game.setScreen(this.gameOverScreen);
    }
}
