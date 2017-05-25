package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.View.Scenes.Hud;
import com.mygdx.game.Utils.CONSTANTS;
import com.mygdx.game.LibgdxWrapper.*;

/**
 * Created by Erik on 05/04/2017.
 */
public class PlayScreen implements Screen {


    private SpriteBatch batch;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private LibgdxWorld gameWorld;

    private float limit;
    private int timeStep = 0;

    private Hud hud;

    private Box2DDebugRenderer b2dr;
    private OrthogonalTiledMapRenderer renderer;
    private Stage stage;

    public PlayScreen( LibgdxWorld gameWorld) {

        batch = new SpriteBatch();

        this.gameWorld = gameWorld;
        gameCam = new OrthographicCamera();
        hud = new Hud();
        gamePort = new FitViewport(CONSTANTS.WIDTH / CONSTANTS.PPM, CONSTANTS.HEIGHT / CONSTANTS.PPM, gameCam);
        stage = new Stage(gamePort);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        renderer = new OrthogonalTiledMapRenderer(gameWorld.getMap(), 1 / CONSTANTS.PPM);
        b2dr = new Box2DDebugRenderer();


        this.gameWorld = gameWorld;

        limit = gameWorld.getxPositionOfLastBody() - gamePort.getScreenWidth() / 200 - 40;// kan ske i world med hjälp av CONSTANTS.WIDTH istället.
    }


    public void update(float dt) {
        gameCam.position.x = gameWorld.getPlayerCharacter().getB2Body().getPosition().x;
        gameWorld.removeBulletsOutSideScreen(gameCam.position.x, gameCam.position.y, gamePort.getScreenWidth(), gamePort.getScreenHeight());

        hud.setScore(gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore());
        hud.setHealth(gameWorld.getPlayerCharacter().getModel().getHealth());

        stepTime();
        gameCam.update();//PlayScreen
        renderer.setView(gameCam);//PlayScreen
    }

    public void stepTime() {
        if (timeStep >= 60) {
            hud.stepWorldTimer();
            timeStep = 0;
        }
        timeStep++;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(gameWorld.getWorld(), gameCam.combined);
        renderDynamicBodies();
        hud.stage.draw();
    }

    private void renderDynamicBodies() {
        Array<Body> bodies = new Array<Body>();
        gameWorld.getWorld().getBodies(bodies);
        for (Body body : bodies) {
            if (body.getType().getValue() == 2) {
                if (!body.isBullet()) {
                    batch.begin();
                    batch.draw(((TextureObject) body.getUserData()).getTexture(),
                            (body.getPosition().x - gameCam.position.x) * 100 + CONSTANTS.WIDTH / 2 - ((TextureObject) body.getUserData()).getSize() * 100,
                            body.getPosition().y * 100 - ((TextureObject) body.getUserData()).getSize() * 100);
                    batch.end();
                } else {
                    batch.begin();
                    batch.draw(((TextureObject) body.getUserData()).getTexture(),
                            (body.getPosition().x - gameCam.position.x) * 100 + CONSTANTS.WIDTH / 2 - ((TextureObject) body.getUserData()).getTexture().getWidth() / 2,
                            body.getPosition().y * 100 - ((TextureObject) body.getUserData()).getTexture().getHeight() / 2);
                    batch.end();
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

    public OrthographicCamera getCam() {
        return gameCam;
    }

    public Viewport getViewport() {
        return gamePort;
    }

    public Stage getStage(){return stage;}

}