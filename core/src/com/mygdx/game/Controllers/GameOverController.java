package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.libgdx.libgdx_world;

/**
 * Created by lucasr on 5/17/17.
 */
public class GameOverController implements IController {

    private GameOverScreen gameOverScreen;
    private Dash game;

    public GameOverController(Dash game){
        this.game = game;
        this.gameOverScreen = new GameOverScreen(game.getPlayerController().getGameWorld());
    }
    public void handleInput(float dt){
        Gdx.input.setInputProcessor(gameOverScreen.getStage());

        gameOverScreen.getStage().getActors().get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("alksdlkasdj");
                game.setController(new PlayerController(game));

            }
        });
        gameOverScreen.getStage().getActors().get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        gameOverScreen.update(dt);
    }
    public void setScreen(){
        game.setScreen(gameOverScreen);
    }
}
