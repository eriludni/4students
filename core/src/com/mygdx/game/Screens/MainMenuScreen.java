package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Controllers.MenuController;
import com.mygdx.game.Dash;
import com.mygdx.game.Screens.MenuSkins.MainMenuSkins;
import com.mygdx.game.libgdx.libgdx_world;


/**
 * Created by Erik on 16/05/2017.
 */
public class MainMenuScreen implements Screen {

    private Viewport viewPort;
    private Stage stage;
    private libgdx_world gameWorld;
    private Dash game;
    private MainMenuSkins menuSkins;
    private MenuController MC;

    public MainMenuScreen(Dash game, libgdx_world gameWorld) {
        this.game = game;
        this.gameWorld = gameWorld;

        viewPort = new FitViewport(Dash.WIDTH, Dash.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, game.batch);
        MC = new MenuController(viewPort, this);

        menuSkins = new MainMenuSkins();

    }

    @Override
    public void show() {


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        TextButton newGameButton = new TextButton("New game", menuSkins.getSkins());
        newGameButton.setPosition(Dash.WIDTH / 2 - Dash.WIDTH / 8, Dash.HEIGHT / 2);

        TextButton quitGameButton = new TextButton("Quit game", menuSkins.getSkins());
        quitGameButton.setPosition(Dash.WIDTH / 2 - Dash.WIDTH / 8, Dash.HEIGHT / 2 - Dash.HEIGHT / 10);

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Dash) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game, gameWorld));
            }
        });
        quitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        ;


        mainTable.add(newGameButton);
        mainTable.row();
        mainTable.add(quitGameButton);

        stage.addActor(mainTable);

    }

    public void update(float delta) {
        MC.handleInput(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return this.stage;
    }
}
