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
import com.mygdx.game.Controllers.GameOverController;
import com.mygdx.game.Controllers.Dash;
import com.mygdx.game.Screens.MenuSkins.GameOverSkins;
import com.mygdx.game.Utils.CONSTANTS;
import com.mygdx.game.libgdx.libgdx_world;

/**
 * Created by lucasr on 5/17/17.
 */
public class GameOverScreen implements Screen {

    private Viewport viewPort;
    private Stage stage;
    private libgdx_world gameWorld;
    private Dash game;
    private GameOverSkins gameOverSkin;
    private GameOverController GOC;
    private int score;

    private Label gameOverLabel;
    private Label highscoreLabel;
    private Label scoreLabel;

    public GameOverScreen(Dash game, libgdx_world gameWorld) {
        this.game = game;
        this.gameWorld = gameWorld;

        viewPort = new FitViewport(CONSTANTS.WIDTH, CONSTANTS.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, game.batch);
        GOC = new GameOverController(viewPort, this);

        gameOverSkin = new GameOverSkins();
    }

    @Override
    public void show() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        score = gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore();

        gameOverLabel = new Label("GAME OVER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label("Highscore: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highscoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        TextButton newGameButton = new TextButton("New game", gameOverSkin.getSkins());
        newGameButton.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2);

        TextButton quitGameButton = new TextButton("Quit game", gameOverSkin.getSkins());
        quitGameButton.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2 - CONSTANTS.HEIGHT / 10);

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Dash) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
                dispose();
            }
        });
        quitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        mainTable.add(gameOverLabel);
        mainTable.row();
        mainTable.add(scoreLabel);
        mainTable.row();
        mainTable.add(highscoreLabel);
        mainTable.row();
        mainTable.add(newGameButton);
        mainTable.row();
        mainTable.add(quitGameButton);

        stage.addActor(mainTable);

    }

    public void update(float dt) {
        GOC.handleInput(dt);
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
        return stage;
    }
}
