package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.Player;

/**
 * Created by Erik on 05/04/2017.
 */
public class PlayScreen implements Screen {

    private Dash game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Player playerCharacter;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Dash game){
        this.game  = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Dash.WIDTH/Dash.PPM,Dash.HEIGHT/Dash.PPM, gameCam);
        maploader  = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/Dash.PPM);



        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();


        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        playerCharacter = new Player(world);

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set((rect.getX()+rect.getWidth() / 2)/Dash.PPM, (rect.getY()+ rect.getHeight() / 2)/Dash.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox((rect.getWidth() /2)/Dash.PPM, (rect.getHeight() /2)/Dash.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);
        }
    }

    public void handleInput(float dt){
       if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
           playerCharacter.b2body.applyLinearImpulse(new Vector2(0, 4f), playerCharacter.b2body.getWorldCenter(),true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerCharacter.b2body.getLinearVelocity().x <= 2)
            playerCharacter.b2body.applyLinearImpulse(new Vector2(0.1f, 0), playerCharacter.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerCharacter.b2body.getLinearVelocity().x >= -2)
            playerCharacter.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), playerCharacter.b2body.getWorldCenter(), true);

    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);
        gameCam.position.x = playerCharacter.b2body.getPosition().x;

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

    public World getWorld(){
        return this.world;
    }
}
