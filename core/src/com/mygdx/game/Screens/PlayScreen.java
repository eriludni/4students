package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
//<<<<<<< Updated upstream
//=======
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//>>>>>>> Stashed changes
import com.mygdx.game.Controllers.PlayerController;
import com.mygdx.game.Dash;
import com.mygdx.game.Model.GameWorld;
import com.mygdx.game.Model.Player;
import com.mygdx.game.Model.PowerUp;
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
    private int timeStep = 0;

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

    public PlayScreen(Dash game) {
        this.game = game;
        this.gameWorld = new libgdx_world(game, new GameWorld());
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
        PC.handleInput(dt);//sätt i kontroller
        gameWorld.update(dt);//sätt i kontroller

        gameCam.position.x = gameWorld.getPlayerCharacter().getB2Body().getPosition().x;

        gameWorld.removeBulletsOutSideScreen(gameCam.position.x, gameCam.position.y, gamePort.getScreenWidth(), gamePort.getScreenHeight());// sätt i kontroller.

        if(gameWorld.getPlayerCharacter().getModel().isDead()) {
            createNewGameOverScreen();
        }

        hud.setScore(gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore());
        hud.setHealth(gameWorld.getPlayerCharacter().getPlayerModel().getHealth());

        stepTime();


        gameCam.update();//PlayScreen
        renderer.setView(gameCam);//PlayScreen
    }

    public void stepTime() {
        if(timeStep >= 60) {
            hud.stepWorldTimer();
            timeStep = 0;
        }
        timeStep++;
    }

    public void createNewGameOverScreen() {
        game.setScreen(new GameOverScreen(game, gameWorld));
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

        renderDynamicBodies();
        
        hud.stage.draw();
    }

    private void renderDynamicBodies(){
        SpriteBatch batch = new SpriteBatch();
        Array<Body> bodies = new Array<Body>();
        gameWorld.getWorld().getBodies(bodies);
        for(Body body : bodies){
            if(body.getType().getValue() == 2) {
                if(!body.isBullet()){
                game.batch.begin();
                game.batch.draw(((TextureObject) body.getUserData()).getTexture(), (body.getPosition().x - gameCam.position.x) * 100 + Dash.WIDTH/2 - ((TextureObject) body.getUserData()).getSize() * 100, body.getPosition().y * 100 - ((TextureObject) body.getUserData()).getSize() * 100);
                game.batch.end();}
                else{
                    game.batch.begin();
                    game.batch.draw(((TextureObject) body.getUserData()).getTexture(), (body.getPosition().x - gameCam.position.x) * 100 + Dash.WIDTH/2 - ((TextureObject) body.getUserData()).getTexture().getWidth()/2, body.getPosition().y * 100 - ((TextureObject) body.getUserData()).getTexture().getHeight()/2);
                    game.batch.end();
                }
            }
        }

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