package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.View.PauseScreen;

/**
 * Created by Erik on 2017-05-22.
 */
public class PauseController implements IController {
    private PauseScreen pauseMenu;
    private Dash game;

    public PauseController(Dash game){
        this.game = game;
        this.pauseMenu = new PauseScreen();

        setListeners(game, pauseMenu);

    }
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(pauseMenu.getStage());
        pauseMenu.update(dt);
    }

    private void setListeners(final Dash game, PauseScreen menu){
        menu.getStage().getActors().get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getPlayerController());
                System.out.println("Yolololol");
            }
        });
        menu.getStage().getActors().get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getMainMenuController());
            }
        });
    }
    public void setScreen(){
        game.setScreen(pauseMenu);
    }
}
