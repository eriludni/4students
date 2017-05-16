package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
//<<<<<<< Updated upstream
//=======
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//>>>>>>> Stashed changes
import com.mygdx.game.Controllers.PlayerController;
import com.mygdx.game.Dash;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.libgdx.*;

import java.util.ArrayList;

/**
 * Created by Erik on 05/04/2017.
 */
public class PlayScreen implements Screen {

    private Dash game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private libgdx_world gameWorld;
    private PlayerController PC;

    private float limit;

    private Hud hud;

    private ArrayList<libgdx_enemyBrain> EB;
    private ArrayList<libgdx_enemy> enemies;

    private Box2DDebugRenderer b2dr;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Dash game, libgdx_world gameWorld) {
        this.game = game;
        this.gameWorld = gameWorld;
        gameCam = new OrthographicCamera();
        hud = new Hud(game.batch);
        gamePort = new FitViewport(Dash.WIDTH/Dash.PPM, Dash.HEIGHT /Dash.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        renderer = new OrthogonalTiledMapRenderer(gameWorld.getMap(), 1 / Dash.PPM);
        b2dr = new Box2DDebugRenderer();

        enemies = gameWorld.getEnemyCharacters();

        PC = new PlayerController(gameWorld, gameCam, gamePort);//handle mouseinput kan ske här istället
        EB = gameWorld.getEB();
        limit = gameWorld.getxPositionOfLastBody() - gamePort.getScreenWidth() / 200 - 40;// kan ske i world med hjälp av Dash.width istället.
    }

    public void update(float dt) {
        PC.handleInput(dt);//dash
        gameWorld.update();//dash

        gameCam.position.x = gameWorld.getPlayerCharacter().getB2Body().getPosition().x;

        gameWorld.removeBulletsOutSideScreen(gameCam.position.x, gameCam.position.y, gamePort.getScreenWidth(), gamePort.getScreenHeight());



        hud.setScore(gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore());

        gameCam.update();//PlayScreen
        renderer.setView(gameCam);//PlayScreen
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        renderer.render();

        b2dr.render(gameWorld.getWorld(), gameCam.combined);
        hud.stage.draw();
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