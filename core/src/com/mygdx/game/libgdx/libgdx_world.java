package com.mygdx.game.libgdx;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dash;
import com.mygdx.game.Model.Enemy;
import com.mygdx.game.Model.GameWorld;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lucas on 2017-05-05.
 */
public class libgdx_world {
    private Dash game;
    private World world;

    private float triggerPos;

    private libgdx_player playerCharacter;

    private ArrayList<Enemy> logicalEnemies;
    private ArrayList<libgdx_enemy> enemyCharacters = new ArrayList<libgdx_enemy>();
    private ArrayList<libgdx_enemyBrain> EB = new ArrayList<libgdx_enemyBrain>();

    private libgdx_map mapCreator;
    private ArrayList<libgdx_map> mapList;

    private static libgdx_world lgdxWorld;
    private GameWorld logicalWorld;

    private int xPositionOfLastBody = 0;

    private MyContactListener MCL;
    private Random rand = new Random();

    private TiledMap map;

    public libgdx_world(Dash game, GameWorld logicalWorld) {

        triggerPos = getxPositionOfLastBody() - Dash.WIDTH / 200 - 40;

        this.game = game;
        this.world = new World(new Vector2(0, -10), true);
        this.logicalWorld = logicalWorld;
        //this.mapCreator = new libgdx_map();
        mapList = new ArrayList<libgdx_map>();
        mapList.add(new libgdx_map());
        this.map = mapList.get(0).getMap();
        createGroundHitbox(mapList.get(0), 0);

        logicalEnemies = logicalWorld.getLogicalEnemies();

        this.lgdxWorld = this;
        this.playerCharacter = new libgdx_player(logicalWorld.getLogicalPlayerCharacter());

        createLibgdxEnemies();
        //createEnemyBrains();

        MCL = new MyContactListener(world);
        this.world.setContactListener(MCL);
    }

    public void update(float dt){
        playerCharacter.update();
        world.step(1 / 60f, 6, 2);
        boolean hasReachedTriggerPos = playerCharacter.getB2Body().getPosition().x > triggerPos;
        if (hasReachedTriggerPos) {
            triggerGeneration();
        }
        if(playerCharacter.getPlayerModel().getRespawnEnemies()) {
            respawnAllEnemies();
            playerCharacter.getPlayerModel().setRespawnEnemies(false);
        }
        for(int i = 0; i < enemyCharacters.size(); i++) {
            enemyCharacters.get(i).update(dt);
        }
        respawnEnemies();
    }



    private void triggerGeneration(){
        generateNewWorldSection();
        triggerPos = getxPositionOfLastBody() - Dash.WIDTH / (2 * Dash.PPM);

    }

    public void createLibgdxEnemies() {
        for(int i = 0; i < logicalWorld.getEnemyCount(); i++) {
            Enemy logicalEnemy = logicalWorld.getLogicalEnemies().get(i);
            enemyCharacters.add(new libgdx_enemy(logicalEnemy));
        }
    }

    public void createEnemyBrains() {
        for(int i = 0; i < logicalWorld.getEnemyCount(); i++) {
            EB.add(new libgdx_enemyBrain(enemyCharacters.get(i)));
        }
    }

    private void createGroundHitbox(libgdx_map currentMap, int offsetX){
        xPositionOfLastBody = (int)(((currentMap.getMapWidth() + offsetX) * 32 + 16) / Dash.PPM);
        BodyDef bdf = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (int x = 0; x < currentMap.getMapWidth(); x++) {
            for (int y = 0; y < currentMap.getMapHeight(); y++) {
                if (y == 0){
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(((x + offsetX) * 32 + 16) / Dash.PPM, ((currentMap.getMapHeight() - y) * 32 + 16) / Dash.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / Dash.PPM, 16 / Dash.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                }
                if (currentMap.getArrayId(x, y) == 1 ||currentMap.getArrayId(x, y) == 3  ) {
                    bdf.type = BodyDef.BodyType.StaticBody;
                    bdf.position.set(((x + offsetX) * 32 + 16) / Dash.PPM, ((currentMap.getMapHeight() - y) * 32 + 16) / Dash.PPM);

                    body = world.createBody(bdf);

                    shape.setAsBox(16 / Dash.PPM, 16 / Dash.PPM);
                    fdef.shape = shape;
                    body.createFixture(fdef);

                    //body.setUserData("GroundEdge");
                    body.setUserData(this);
                }
            }
        }
    }

    public void removeAllLibgdxEnemies() {
        libgdx_enemy enemy;
        for(int i = 0; i < enemyCharacters.size(); i++) {
            enemy = enemyCharacters.get(i);
            enemyCharacters.remove(enemy);
            getWorld().destroyBody(enemy.getB2Body());
            //enemyCharacters.get(i).getEnemyModel().setDead(true);
        }
        enemyCharacters = new ArrayList<libgdx_enemy>();
    }

    public void removeAllLibgdxEnemyBrains() {
        for(int i = 0; i < EB.size(); i++) {
            EB.remove(i);
        }
    }

    public void mapListManager(){

    }

    public void generateNewWorldSection(){
        mapList.get(0).setNextlibgdx_map();
        int offsetX = mapList.get(0).getOffsetX();
        createGroundHitbox(mapList.get(0), offsetX);
    }

    public void respawnAllEnemies() {
        //lgdxWorld.removeAllLibgdxEnemyBrains();
        System.out.println("respawn");
        removeAllLibgdxEnemies();
        lgdxWorld.getLogicalWorld().removeAllLogicalEnemyBrains();
        lgdxWorld.getLogicalWorld().removeAllLogicalEnemies();
        lgdxWorld.getLogicalWorld().createLogicalEnemies();
        lgdxWorld.getLogicalWorld().createLogicalEnemyBrains();
        createLibgdxEnemies();
        MCL.setLgdxEnemies(getEnemyCharacters());
        //lgdxWorld.createEnemyBrains();
    }

    public void removeBulletsOutSideScreen(float gameCamPositionX, float gameCamPositionY, float screenWidth, float screenHeight) {//world
        Array<Body> bodies = new Array<Body>(10);
        world.getBodies(bodies);
        for (int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            if (body != null && body.isBullet()) {
                //libgdx_body_userdata data = (libgdx_body_userdata) body.getUserData();
                libgdx_projectile data = (libgdx_projectile) body.getUserData();
                boolean bulletOutOfBounds = body.getPosition().x < gameCamPositionX - screenWidth / (2 * Dash.PPM) ||
                        body.getPosition().x > gameCamPositionX + screenWidth / (2 * Dash.PPM) ||
                        body.getPosition().y < 0 ||
                        body.getPosition().y > gameCamPositionY + screenHeight / (2 * Dash.PPM);

                if (data.isSetForRemoval() || bulletOutOfBounds) {
                    world.destroyBody(body);
                    body.setUserData(null);
                    body = null;
                }
            }
        }
    }

    public void respawnEnemies() {//world
        ArrayList<libgdx_enemy> enemies;
        enemies = getEnemyCharacters();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getEnemyModel().isDead()||
                    enemies.get(i).getB2Body().getPosition().y <= 0) {
                libgdx_enemy enemy = enemies.get(i);
                enemies.remove(enemy);
                world.destroyBody(enemy.getB2Body());
                logicalWorld.getLogicalPlayerCharacter().setHighscore(
                        logicalWorld.getLogicalPlayerCharacter().getHighscore() + 100);

                int distance = (rand.nextInt(3) + 3) * 100;

                enemies.add(new libgdx_enemy(new Enemy(3, 0.1f, 0,
                        logicalWorld.getLogicalPlayerCharacter().getXPos() * Dash.PPM + distance,
                        logicalWorld.getLogicalPlayerCharacter().getYPos() * Dash.PPM + 50,
                        10)));
            }
        }
    }

    public int getxPositionOfLastBody() {
        return xPositionOfLastBody;
    }

    public static libgdx_world getlgdxWorld() {
        return lgdxWorld;
    }

    public GameWorld getLogicalWorld() {
        return logicalWorld;
    }

    public libgdx_player getPlayerCharacter() {
        return playerCharacter;
    }

    public ArrayList<libgdx_enemy> getEnemyCharacters() {
        return enemyCharacters;
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public ArrayList<libgdx_enemyBrain> getEB() {
        return EB;
    }
}
