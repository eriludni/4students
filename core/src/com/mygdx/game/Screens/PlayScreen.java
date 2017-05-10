package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.Controllers.PlayerController;
import com.mygdx.game.libgdx.Dash;
import com.mygdx.game.Model.EnemyBrain;
import com.mygdx.game.libgdx.libgdx_enemyBrain;
import com.mygdx.game.libgdx.libgdx_world;

/**
 * Created by Erik on 05/04/2017.
 */
public class PlayScreen implements Screen {

    private Dash game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private libgdx_world gameWorld;
    private PlayerController PC;

    private libgdx_enemyBrain EB;

    private Box2DDebugRenderer b2dr;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Dash game, libgdx_world gameWorld) {
        this.game = game;
        this.gameWorld = gameWorld;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Dash.WIDTH / Dash.PPM, Dash.HEIGHT / Dash.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        renderer = new OrthogonalTiledMapRenderer(gameWorld.getMap(), 1 / Dash.PPM);
        b2dr = new Box2DDebugRenderer();

        PC = new PlayerController(gameWorld);
        EB = new libgdx_enemyBrain(gameWorld.getEnemyCharacter());
    }


    public void update(float dt) {
        PC.handleInput(dt);

        gameWorld.getWorld().step(1 / 60f, 6, 2);
        gameCam.position.x = gameWorld.getPlayerCharacter().getB2Body().getPosition().x;

        EB.update(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        game.batch.setProjectionMatrix(gameCam.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(gameWorld.getWorld(), gameCam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

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

    }

}
