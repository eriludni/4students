package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
//<<<<<<< Updated upstream
//=======
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//>>>>>>> Stashed changes
import com.mygdx.game.Controllers.PlayerController;
import com.mygdx.game.Model.Enemy;
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

    private int limit;

    private Hud hud;

    private ArrayList<libgdx_enemyBrain> EB = new ArrayList<libgdx_enemyBrain>();
    private ArrayList<libgdx_enemy> enemies;

    private Box2DDebugRenderer b2dr;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Dash game, libgdx_world gameWorld) {
        this.game = game;
        this.gameWorld = gameWorld;
        gameCam = new OrthographicCamera();
        hud = new Hud(game.batch);
        gamePort = new FitViewport(Dash.WIDTH / Dash.PPM, Dash.HEIGHT / Dash.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        renderer = new OrthogonalTiledMapRenderer(gameWorld.getMap(), 1 / Dash.PPM);
        b2dr = new Box2DDebugRenderer();

        enemies = gameWorld.getEnemyCharacters();

        PC = new PlayerController(gameWorld, gameCam, gamePort);
        //EB = new libgdx_enemyBrain(gameWorld.getEnemyCharacter());
        createEnemyBrains();
        limit = gameWorld.getxPositionOfLastBody() - gamePort.getScreenWidth()/200 - 40;
    }

    public void update(float dt) {
        PC.handleInput(dt);
        gameWorld.getPlayerCharacter().update(dt);

        //System.out.println("HEJSAN:" + ((int) gameCam.position.x > limit));
        if( gameCam.position.x > limit)
        {
            gameWorld.updateWorld();
            limit = gameWorld.getxPositionOfLastBody() - gamePort.getScreenWidth()/200 - 20;
        }

        gameWorld.getWorld().step(1 / 60f, 6, 2);
        gameCam.position.x = gameWorld.getPlayerCharacter().getB2Body().getPosition().x;

        removeBullets();
        removeEnemies();

        for(int i = 0; i < EB.size(); i++) {
            EB.get(i).update(dt);
        }

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(dt);
            if(enemies.get(i).getB2Body().getPosition().y <= 0) {
                enemies.get(i).getEnemyModel().setDead(true);
            }
        }

        hud.setScore(gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore());

        gameCam.update();
        renderer.setView(gameCam);
    }

    public void createEnemyBrains() {
        for(int i = 0; i < gameWorld.getEnemyCharacters().size(); i++) {
            EB.add(new libgdx_enemyBrain(enemies.get(i)));
        }
    }

    public void removeBullets() {
        Array<Body> bodies = new Array<Body>(10);
        gameWorld.getWorld().getBodies(bodies);
        for (int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            if (body != null && body.isBullet()) {
                //libgdx_body_userdata data = (libgdx_body_userdata) body.getUserData();
                libgdx_projectile data = (libgdx_projectile) body.getUserData();
                boolean bulletOutOfBounds = body.getPosition().x < (int)(gameCam.position.x) - gamePort.getScreenWidth()/(2*Dash.PPM)||
                        body.getPosition().x > (int)(gameCam.position.x) + gamePort.getScreenWidth()/(2*Dash.PPM) ||
                        body.getPosition().y < 0 ||
                        body.getPosition().y > (int)(gameCam.position.y) + gamePort.getScreenHeight()/(2*Dash.PPM);

                if (data.isSetForRemoval() || bulletOutOfBounds) {
                    gameWorld.getWorld().destroyBody(body);
                    body.setUserData(null);
                    body = null;
                }
            }
        }
    }

    public void removeEnemies() {
        ArrayList<libgdx_enemy> enemies;
        enemies = gameWorld.getEnemyCharacters();

        for(int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).getEnemyModel().isDead()) {
                libgdx_enemy enemy = enemies.get(i);
                enemies.remove(enemy);
                gameWorld.getWorld().destroyBody(enemy.getB2Body());
                gameWorld.getLogicalWorld().getLogicalPlayerCharacter().setHighscore(gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore() + 100);
                enemies.add(new libgdx_enemy(new Enemy(3, 0.1f, 0, gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getXPos() * 170, gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getYPos() +300, 10)));
            }
        }
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