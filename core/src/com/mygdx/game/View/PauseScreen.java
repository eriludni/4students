package com.mygdx.game.View;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.View.MenuSkins.MainMenuSkins;
import com.mygdx.game.Utils.CONSTANTS;

/**
 * Created by Erik on 18/05/2017.
 */
public class PauseScreen implements Screen {
    private Viewport viewPort;
    private Stage stage;
    private MainMenuSkins menuSkins;

    public PauseScreen() {

        viewPort = new FitViewport(CONSTANTS.WIDTH, CONSTANTS.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort);

        menuSkins = new MainMenuSkins();
        createMenu();

    }

    private void createMenu(){
        TextButton resumeGame = new TextButton("Resume", menuSkins.getSkins());
        resumeGame.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2);

        TextButton quitToMenuButton = new TextButton("Quit to Menu", menuSkins.getSkins());
        quitToMenuButton.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2 - CONSTANTS.HEIGHT / 10);

        TextButton restartGameButton = new TextButton("Restart game", menuSkins.getSkins());
        quitToMenuButton.setPosition(CONSTANTS.WIDTH / 2 - CONSTANTS.WIDTH / 8, CONSTANTS.HEIGHT / 2 - CONSTANTS.HEIGHT / 5);

        stage.addActor(resumeGame);
        stage.addActor(quitToMenuButton);
        stage.addActor(restartGameButton);
    }

    @Override
    public void show() {
    }

    public void update(float delta) {
        render(delta);
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
        return this.stage;
    }

}
