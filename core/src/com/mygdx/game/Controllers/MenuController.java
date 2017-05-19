package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Dash;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Erik on 16/05/2017.
 */
public class MenuController {

    private Viewport viewPort;
    MainMenuScreen menu;

    public MenuController( Viewport viewPort, MainMenuScreen menu){
        this.menu = menu;
        this.viewPort = viewPort;

    }
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(menu.getStage());
        menu.getStage().getActors().get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Dash) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(menu.getGame(), menu.GetWorld()));
            }
        });


    }



}
