package com.mygdx.game.View;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.LibgdxWrapper.LibgdxWorld;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.View.MenuSkins.GameOverSkins;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by lucasr on 5/17/17.
 */
public class GameOverScreen implements Screen {

    private Viewport viewPort;
    private Stage stage;
    private GameWorld gameWorld;
    private GameOverSkins gameOverSkin;
    private int score;

    private Label gameOverLabel;
    private Label highscoreLabel;
    private Label scoreLabel;

    public GameOverScreen(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        viewPort = new FitViewport(CONSTANTS.WIDTH, CONSTANTS.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort);
        gameOverSkin = new GameOverSkins();
        createMenu();
    }

    @Override
    public void show() {

    }

    private void createMenu(){
        score = gameWorld.getLogicalPlayerCharacter().getHighscore();
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(100);
        gameOverLabel = new Label("GAME OVER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label("Highscore: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highscoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        TextButton newGameButton = new TextButton("New game", gameOverSkin.getSkins());
        newGameButton.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2);

        TextButton quitGameButton = new TextButton("Quit game", gameOverSkin.getSkins());
        quitGameButton.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2 - CONSTANTS.HEIGHT / 10);

        stage.addActor(newGameButton);
        stage.addActor(quitGameButton);

        mainTable.add(gameOverLabel);
        mainTable.row();
        mainTable.add(scoreLabel);
        mainTable.row();
        mainTable.add(highscoreLabel);
        stage.addActor(mainTable);

    }

    public void update(float dt) {
        render(dt);
        score = gameWorld.getLogicalPlayerCharacter().getHighscore();
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
