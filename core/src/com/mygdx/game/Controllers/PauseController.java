package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.View.PauseScreen;

/**
 * Created by Erik on 2017-05-22.
 *
 * @author Erik Lundin
 * Responsibility: Handles how the pause screen responds to input from the user
 * Uses: IController, PauseScreen, Dash
 * Used by: Dash
 */
public class PauseController implements IController {
    private PauseScreen pauseMenu;
    private Dash game;

    public PauseController(Dash game){
        this.game = game;
        this.pauseMenu = new PauseScreen();

        setListeners();

    }

    /**
     *Update method for controller
     */
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(pauseMenu.getStage());
        pauseMenu.update(dt);
    }


    /**
     * create Listeners for buttons
     */
    private void setListeners(){
        pauseMenu.getStage().getActors().get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getPlayerController());
            }
        });
        pauseMenu.getStage().getActors().get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getMainMenuController());
            }
        });
        pauseMenu.getStage().getActors().get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getNewPlayerController());
            }
        });
    }

    /**
     * Setter
     */
    public void setScreen(){
        game.setScreen(pauseMenu);
        Gdx.input.setInputProcessor(pauseMenu.getStage());
    }
}
