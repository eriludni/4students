package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.MainMenuScreen;

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

    }



}
