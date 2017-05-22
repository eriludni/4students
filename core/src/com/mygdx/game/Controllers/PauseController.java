package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.PauseScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.libgdx.libgdx_world;

/**
 * Created by Erik on 2017-05-22.
 */
public class PauseController implements IController {
    private PauseScreen pauseScreen;

    public PauseController(Dash game, libgdx_world world){
        this.pauseScreen = new PauseScreen();
        game.setScreen(pauseScreen);



    }
    public void handleInput(float dt){

    }
    public void setScreen(){

    }
}
