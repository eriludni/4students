package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.View.MainMenuScreen;

/**
 * Created by Erik on 16/05/2017.
 */
public class MenuController implements IController {

    private MainMenuScreen menu;
    private Dash game;

    public MenuController(Dash game){
        this.game = game;
        this.menu = new MainMenuScreen();

        setListeners(game, menu);

    }
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(menu.getStage());
        menu.update(dt);
    }

    private void setListeners(final Dash game, MainMenuScreen menu){
        menu.getStage().getActors().get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setController(game.getNewPlayerController());
            }
        });
        menu.getStage().getActors().get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }
    public void setScreen(){
        game.setScreen(menu);
    }



}
