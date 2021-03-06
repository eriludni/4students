package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.View.Scenes.Hud;
import com.mygdx.game.Utils.CONSTANTS;
import com.mygdx.game.LibgdxWrapper.*;

import java.util.Hashtable;

/**
 * Created by Erik on 05/04/2017.
 *
 * @author Erik Lundin
 *         Responsibility: Handles the view of the game when it is being played
 *         Uses: Hud, LibgdxWorld, CONSTANTS
 *         Used by: PlayerController
 */
public class PlayScreen implements Screen {
    private SpriteBatch batch;
    private Hashtable<Integer, TextureRegion> textures;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private LibgdxWorld gameWorld;
    private int timeStep = 0;
    private Texture spritesheet;
    private TextureRegion[] regions = new TextureRegion[7];

    private Hud hud;

    private Stage stage;

    public PlayScreen(LibgdxWorld gameWorld) {
        batch = new SpriteBatch();
        spritesheet = new Texture("Tiles_32x32.png");


        this.gameWorld = gameWorld;
        gameCam = new OrthographicCamera();
        hud = new Hud();
        gamePort = new FitViewport(CONSTANTS.WIDTH / CONSTANTS.PPM, CONSTANTS.HEIGHT / CONSTANTS.PPM, gameCam);
        stage = new Stage(gamePort);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2 + 32f / CONSTANTS.PPM, 0);
        initiateHashTable();


        this.gameWorld = gameWorld;
    }

    /**
     * Initiates the hashtable with ID numbers for bodies and their corresponding texture.
     */
    private void initiateHashTable() {
        textures = new Hashtable<Integer, TextureRegion>();


        regions[0] = new TextureRegion(spritesheet, 0, 0, 32, 32); //playerTexture
        regions[1] = new TextureRegion(spritesheet,96,192,32,32); //enemyTexture
        regions[2] = new TextureRegion(spritesheet,0,64,32,32); //projectileTexture
        regions[3] = new TextureRegion(spritesheet,0,32,32,32); //powerUpTexture
        regions[4] = new TextureRegion(spritesheet,32,0,32,96); //groundTexture
        regions[5] = new TextureRegion(spritesheet,192,0,32,32); //platformTexture
        regions[6] = new TextureRegion(spritesheet,0,160,96,32); //cloud


        textures.put(1, regions[0]);
        textures.put(2, regions[1]);
        textures.put(3, regions[2]);
        textures.put(4, regions[3]);
        textures.put(5, regions[4]);
        textures.put(6, regions[5]);
        textures.put(7, regions[6]);

    }


    public void update(float dt) {
        gameCam.position.x = gameWorld.getPlayerCharacter().getB2Body().getPosition().x;
        gameWorld.removeBulletsOutSideScreen(gameCam.position.x, gameCam.position.y, gamePort.getScreenWidth(), gamePort.getScreenHeight());//controller ska fixa det här.

        hud.setScore(gameWorld.getLogicalWorld().getLogicalPlayerCharacter().getHighscore());
        hud.setHealth(gameWorld.getPlayerCharacter().getModel().getHealth());

        stepTime();
        gameCam.update();
    }

    /**
     * Checks if the timeStep variable is greater than or equal to 60 and if it is it increases the worldTimer by 1 and resets the stepTime variable to 0,
     * then it increases the stepTime variable by 1
     * This is done to get the time to correctly update.
     */
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

        Gdx.gl.glClearColor(14 / 255f, 186 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        dangerousTexture();

        renderBodies();
        hud.stage.draw();
    }

    /**
     * Draws a texture that chases the player.
     */
    private void dangerousTexture() {
        float counterPos = gameWorld.getCounter();
        batch.begin();
        batch.draw(regions[2], counterPos * 100 - gameCam.position.x * 100 - CONSTANTS.WIDTH / 2, 32, CONSTANTS.WIDTH, CONSTANTS.HEIGHT);
        batch.end();
    }

    /**
     * Draws a texture to the bodies of the world.
     */
    private void renderBodies() {
        Array<Body> bodies = new Array<Body>();
        gameWorld.getWorld().getBodies(bodies);
        for (Body body : bodies) {
            Drawable drawableobject = (Drawable) body.getUserData();
            int textureKey = drawableobject.getBodyID();
            float xPosition = (body.getPosition().x - gameCam.position.x) * CONSTANTS.PPM + CONSTANTS.WIDTH / 2 - drawableobject.getFixtureWidth() * CONSTANTS.PPM / 2;
            float yPosition = (body.getPosition().y - 32f / CONSTANTS.PPM) * CONSTANTS.PPM - drawableobject.getFixtureHeight() - drawableobject.getFixtureHeight() * CONSTANTS.PPM / 2;
            if (textureKey == 5) {
                yPosition = (body.getPosition().y - 32f / CONSTANTS.PPM) * CONSTANTS.PPM - drawableobject.getFixtureHeight() * 88;
            }

            batch.begin();
            batch.draw(textures.get(textureKey), xPosition, yPosition, drawableobject.getFixtureWidth() * CONSTANTS.PPM, drawableobject.getFixtureHeight() * CONSTANTS.PPM);
            batch.end();
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

    /**
     * Getter
     */
    public OrthographicCamera getCam() {
        return gameCam;
    }

    /**
     * Getter
     */
    public Viewport getViewport() {
        return gamePort;
    }

    /**
     * Getter
     */
    public Stage getStage() {
        return stage;
    }

}