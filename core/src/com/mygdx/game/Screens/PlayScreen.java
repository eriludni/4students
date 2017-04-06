package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Dash;

/**
 * Created by Erik on 05/04/2017.
 */
public class PlayScreen implements Screen {

    private Dash game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private World world;
    private Box2DDebugRenderer b2dr;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Dash game){
        this.game  = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(,600, gameCam);
        maploader  = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(rect.getX()+rect.getWidth() / 2, rect.getY()+ rect.getHeight() / 2);

                    body = world.createBody(bdf);

                    shape.setAsBox(rect.getWidth() /2, rect.getHeight() /2);
                    fdef.shape = shape;
                    body.createFixture(fdef);
        }
    }

    public void handleInput(float dt){
        if (Gdx.input.isKeyPressed(22)){
            gameCam.position.x += 100*dt;
        }

    }

    public void update(float dt){
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);

        world.step(1/60f,6,2);
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

        b2dr.render(world, gameCam.combined);

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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
